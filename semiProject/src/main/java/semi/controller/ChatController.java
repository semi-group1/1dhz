package semi.controller;

import semi.model.dto.ChatMessageDto;
import semi.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public String showChatPage(Model model) {
        model.addAttribute("messages", chatService.getModel().getMessages());
        return "chat";
    }

    @PostMapping("/chat")
    public String addMessage(@RequestParam("user") String user, @RequestParam("message") String message) {
        if (user != null && !user.trim().isEmpty() && message != null && !message.trim().isEmpty()) {
            chatService.addMessage(user, message);
        }
        return "redirect:/chat";
    }
}
