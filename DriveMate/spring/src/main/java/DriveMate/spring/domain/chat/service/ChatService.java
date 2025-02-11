package DriveMate.spring.domain.chat.service;

import DriveMate.spring.domain.chat.dto.ChatRequestDto;
import DriveMate.spring.domain.chat.dto.ChatResponseDto;

public interface ChatService {
    ChatResponseDto.ChatLogResultDto saveChatLog(ChatRequestDto.ChatLogDto request);
    void saveChatSummary(ChatRequestDto.ChatSummaryDto request);
    ChatResponseDto.ChatResultDto getChat(Long chatId);
    void deleteChat(Long chatId);

}
