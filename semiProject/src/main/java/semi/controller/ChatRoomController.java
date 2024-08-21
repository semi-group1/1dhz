package semi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import semi.service.ChatService;

@Controller
public class ChatRoomController {
    private final ChatService chatService;

    @Autowired
    public ChatRoomController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/semiProject/chatrooms")
    public String showChatRooms(Model model) {
        List<Integer> chatRoomIds = chatService.getUniqueChatRoomIds();
        model.addAttribute("chatRoomIds", chatRoomIds);
        return "chatrooms";
    }

    @PostMapping("/semiProject/createChatRoom")
    public String createChatRoom() {
        int maxChatRoomId = chatService.getMaxChatRoomId();
        int newChatRoomId = maxChatRoomId + 1;
        return "redirect:/semiProject/chat?chatRoomId=" + newChatRoomId;
    }
}

