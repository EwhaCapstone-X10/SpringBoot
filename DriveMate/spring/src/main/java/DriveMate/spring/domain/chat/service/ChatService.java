package DriveMate.spring.domain.chat.service;

import DriveMate.spring.common.response.ApiResponse;
import DriveMate.spring.domain.chat.dto.ChatRequestDto;
import DriveMate.spring.domain.chat.dto.ChatResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ChatService {
    ChatResponseDto.ChatLogResultDto saveChatLog(ChatRequestDto.ChatLogDto request);
    void saveChatSummary(ChatRequestDto.ChatSummaryDto request);
    ChatResponseDto.ChatResultDto getChat(Long chatId);
    void deleteChat(Long chatId);
    ResponseEntity<ApiResponse> getChatList(Pageable pageable, Integer year);
}
