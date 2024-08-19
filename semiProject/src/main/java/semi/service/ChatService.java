package semi.service;

import semi.model.ChatModel;
import semi.model.dto.ChatMessageDto;

public class ChatService {
    private final ChatModel model;

    public ChatService(ChatModel model) {
        this.model = model;
    }

    public void addMessage(String user, String message) {
        model.addMessage(new ChatMessageDto(user, message));
    }

    public ChatModel getModel() {
        return model;
    }
}
