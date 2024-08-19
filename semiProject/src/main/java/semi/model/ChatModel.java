package semi.model;

import java.util.ArrayList;
import java.util.List;

import semi.model.dto.ChatMessageDto;

public class ChatModel {
    private List<ChatMessageDto> messages = new ArrayList<>();

    public List<ChatMessageDto> getMessages() {
        return messages;
    }

    public void addMessage(ChatMessageDto message) {
        messages.add(message);
    }
}