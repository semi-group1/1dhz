package semi.model.dto;

import java.sql.Timestamp;

public class ChatMessageDto {
    private int senderId;
    private String senderName;
    private String message;
    private Timestamp sentTime;

    public ChatMessageDto(int senderId, String senderName, String message, Timestamp sentTime) {
        this.senderId = senderId;
        this.senderName = senderName;
        this.message = message;
        this.sentTime = sentTime;
    }
    
    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }
    
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getSentTime() {
        return sentTime;
    }

    public void setSentTime(Timestamp sentTime) {
        this.sentTime = sentTime;
    }
}
