package DriveMate.spring.domain.chat.controller;

import DriveMate.spring.common.response.ApiResponse;
import DriveMate.spring.common.status.SuccessStatus;
import DriveMate.spring.domain.chat.dto.ChatRequestDto;
import DriveMate.spring.domain.chat.dto.ChatResponseDto;
import DriveMate.spring.domain.chat.service.ChatService;
import DriveMate.spring.domain.member.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatRestController {
    private final ChatService chatService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> saveChat(
            @RequestBody ChatRequestDto.ChatLogDto request)
    {
        ChatResponseDto.ChatResultDto response = chatService.saveChatLog(request);
        return ApiResponse.onSuccess(SuccessStatus._OK, response);
    }
}
