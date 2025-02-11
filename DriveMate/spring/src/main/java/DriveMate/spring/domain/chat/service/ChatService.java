package DriveMate.spring.domain.chat.service;

import DriveMate.spring.domain.chat.dto.ChatRequestDto;
import DriveMate.spring.domain.chat.dto.ChatResponseDto;

public interface ChatService {
    ChatResponseDto.ChatLogResultDto saveChatLog(ChatRequestDto.ChatLogDto request);
    ChatResponseDto.ChatResultDto getChat(Long chatId);
}
