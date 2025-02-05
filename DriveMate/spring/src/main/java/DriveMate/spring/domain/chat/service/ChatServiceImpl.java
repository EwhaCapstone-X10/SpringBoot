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
    public ChatResponseDto.ChatResultDto saveChatLog(ChatRequestDto.ChatLogDto request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        ChatLog chatLog = ChatLog.builder()
                .member(member)
                .date(request.getDate())
                .summary(request.getSummary())
                .keywords(request.getKeywords())
                .build();
        ChatLog savedChatLog = chatLogRepository.save(chatLog);

        List<ChatMessage> messages = request.getChatting().stream()
                .map(chatMessageDto -> ChatMessage.builder()
                        .role(chatMessageDto.getRole())
                        .chat(chatMessageDto.getChat())
                        .chatLog(savedChatLog)
                        .build())
                .collect(Collectors.toList());
        chatMessageRepository.saveAll(messages);

        List<ChatRequestDto.ChatMessageDto> chatMessageDtos = messages.stream()
                .map(msg -> new ChatRequestDto.ChatMessageDto(msg.getRole(), msg.getChat()))
                .collect(Collectors.toList());

        return ChatResponseDto.ChatResultDto.builder()
                .memberId(member.getMemberId())
                .date(chatLog.getDate())
                .summary(chatLog.getSummary())
                .keywords(chatLog.getKeywords())
                .chatting(chatMessageDtos)
                .build();
    }
}
