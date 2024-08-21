package semi.service;

import semi.model.dao.ChatMessageDao;
import semi.model.dto.ChatMessageDto;

import java.util.List;

public class ChatService {
    private final ChatMessageDao chatMessageDao;

    public ChatService(ChatMessageDao chatMessageDao) {
        this.chatMessageDao = chatMessageDao;
    }

    public void addMessage(int chatRoomId, int senderId, String message) {
        chatMessageDao.insertMessage(chatRoomId, senderId, message);
    }

    public List<ChatMessageDto> getMessages(int chatRoomId) {
        return chatMessageDao.getMessages(chatRoomId);
    }
    
    public List<Integer> getUniqueChatRoomIds() {
        return chatMessageDao.getUniqueChatRoomIds();
    }
    
    public int getMaxChatRoomId() {
        return chatMessageDao.getMaxChatRoomId();
    }
}
