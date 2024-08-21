package semi.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import semi.model.dto.ChatMessageDto;
import semi.service.ChatService;

@Controller
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public String showChatPage(Model model, @RequestParam(value = "chatRoomId", defaultValue = "1") int chatRoomId) {
        List<ChatMessageDto> messages = chatService.getMessages(chatRoomId);
        model.addAttribute("messages", messages);
        model.addAttribute("chatRoomId", chatRoomId);
        return "chat";
    }

    @PostMapping("/chat")
    public String addMessage(
            @RequestParam("chatRoomId") int chatRoomId,
            @RequestParam("message") String message,
            HttpSession session) {

        Integer senderId = (Integer) session.getAttribute("userId");
        if (senderId != null && message != null && !message.trim().isEmpty()) {
            chatService.addMessage(chatRoomId, senderId, message);
        }
        return "redirect:/chat?chatRoomId=" + chatRoomId;
    }

    @GetMapping("/chat/messages")
    public ResponseEntity<String> getMessages(@RequestParam("chatRoomId") int chatRoomId) {
    	// chat_room_id값에 해당하는 채팅 메시지를 ChatService에서 가져옴
        List<ChatMessageDto> messages = chatService.getMessages(chatRoomId);
        
        StringBuilder sb = new StringBuilder();
        for (ChatMessageDto msg : messages) {
            sb.append("<div class=\"message ").append(msg.getSenderId() == 1 ? "user1" : "user2").append("\">")
              .append("<div class=\"message-header\"><span>").append(msg.getSenderName()).append("</span></div>")
              .append("<div class=\"message-body\">").append(msg.getMessage()).append("</div>")
              .append("<div class=\"message-time\">").append(new SimpleDateFormat("HH:mm").format(msg.getSentTime())).append("</div>")
              .append("</div>");
        }

        // utf-8로 인코딩
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.TEXT_HTML);
        headers.set(HttpHeaders.CONTENT_TYPE, "text/html; charset=UTF-8");

        return new ResponseEntity<>(sb.toString(), headers, HttpStatus.OK);
    }
}
