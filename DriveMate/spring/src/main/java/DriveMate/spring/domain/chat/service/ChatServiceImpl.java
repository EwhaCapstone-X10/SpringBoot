package DriveMate.spring.domain.chat.service;

import DriveMate.spring.common.exception.GeneralException;
import DriveMate.spring.common.status.ErrorStatus;
import DriveMate.spring.domain.chat.dto.ChatRequestDto;
import DriveMate.spring.domain.chat.dto.ChatResponseDto;
import DriveMate.spring.domain.chat.entity.ChatLog;
import DriveMate.spring.domain.chat.entity.ChatMessage;
import DriveMate.spring.domain.chat.repository.ChatLogRepository;
import DriveMate.spring.domain.chat.repository.ChatMessageRepository;
import DriveMate.spring.domain.member.entity.Member;
import DriveMate.spring.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final MemberRepository memberRepository;
    private final ChatLogRepository chatLogRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Override
    @Transactional
    public ChatResponseDto.ChatLogResultDto saveChatLog(ChatRequestDto.ChatLogDto request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        ChatLog chatLog = ChatLog.builder()
                .member(member)
                .date(request.getDate())
                .build();
        ChatLog savedChatLog = chatLogRepository.save(chatLog);

        List<ChatMessage> messages = request.getChatting().stream()
                .map(chatMessageDto -> ChatMessage.builder()
                        .idx(chatMessageDto.getIdx())
                        .role(chatMessageDto.getRole())
                        .chat(chatMessageDto.getChat())
                        .chatLog(savedChatLog)
                        .build())
                .collect(Collectors.toList());
        chatMessageRepository.saveAll(messages);

        List<ChatRequestDto.ChatMessageDto> chatMessageDtos = messages.stream()
                .map(msg -> new ChatRequestDto.ChatMessageDto(msg.getRole(), msg.getChat(), msg.getIdx()))
                .collect(Collectors.toList());

        return ChatResponseDto.ChatLogResultDto.builder()
                .chatId(chatLog.getChatLogId())
                .build();
    }

    @Override
    public void saveChatSummary(ChatRequestDto.ChatSummaryDto request) {
        ChatLog chatLog = chatLogRepository.findById(request.getChatId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.CHAT_NOT_FOUND));

        chatLog.setSummary(request.getSummary());
        chatLog.setKeywords(request.getKeywords());
        chatLogRepository.save(chatLog);
    }

    @Override
    public ChatResponseDto.ChatResultDto getChat(Long chatId) {
        // 추후에 권한 확인 로직 추가

        ChatLog chatLog = chatLogRepository.findById(chatId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.CHAT_NOT_FOUND));

        List<ChatMessage> messages = chatLog.getChatting();
        List<ChatRequestDto.ChatMessageDto> chatMessageDtos = messages.stream()
                .map(msg -> new ChatRequestDto.ChatMessageDto(msg.getRole(), msg.getChat(), msg.getIdx()))
                .collect(Collectors.toList());

        return ChatResponseDto.ChatResultDto.builder()
                .chatId(chatLog.getChatLogId())
                .date(chatLog.getDate())
                .summary(chatLog.getSummary())
                .keywords(chatLog.getKeywords())
                .chatting(chatMessageDtos)
                .build();
    }

    @Override
    public void deleteChat(Long chatId) {
        // 권한 확인
        ChatLog chatLog = chatLogRepository.findById(chatId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.CHAT_NOT_FOUND));

        chatLogRepository.delete(chatLog);
    }
}
