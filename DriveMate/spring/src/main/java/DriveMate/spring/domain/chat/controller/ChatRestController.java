package DriveMate.spring.domain.chat.controller;

import DriveMate.spring.common.response.ApiResponse;
import DriveMate.spring.common.status.SuccessStatus;
import DriveMate.spring.domain.chat.dto.ChatRequestDto;
import DriveMate.spring.domain.chat.dto.ChatResponseDto;
import DriveMate.spring.domain.chat.service.ChatService;
import DriveMate.spring.domain.member.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatRestController {
    private final ChatService chatService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> saveChat(
            @RequestBody ChatRequestDto.ChatLogDto request)
    {
        ChatResponseDto.ChatLogResultDto response = chatService.saveChatLog(request);
        return ApiResponse.onSuccess(SuccessStatus._OK, response);
    }

    @PostMapping("/summary")
    public ResponseEntity<ApiResponse> saveChatSummary(
            @RequestBody ChatRequestDto.ChatSummaryDto request)
    {
        chatService.saveChatSummary(request);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<ApiResponse> getChatinfo(
            @PathVariable Long chatId
    ) {
        ChatResponseDto.ChatResultDto response = chatService.getChat(chatId);
        return ApiResponse.onSuccess(SuccessStatus._OK, response);
    }

    @DeleteMapping("/{chatId}")
    public ResponseEntity<ApiResponse> deleteChat(
            @PathVariable Long chatId
    ) {
        chatService.deleteChat(chatId);
        return ApiResponse.onSuccess(SuccessStatus._DELETED);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse> getChats(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam int year
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "date"));
        return chatService.getChatList(pageable, year);
    }
}
