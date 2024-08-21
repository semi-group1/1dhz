package semi.model.dao;

import semi.model.dto.ChatMessageDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.jdbc.pool.DataSource;

public class ChatMessageDao {
    private DataSource dataSource;

    public ChatMessageDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertMessage(int chatRoomId, int senderId, String message) {
        String sql = "insert into semi_chat (chat_room_id, sender_id, message, sent_time) values (?, ?, ?, systimestamp)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, chatRoomId);
            pstmt.setInt(2, senderId);
            pstmt.setString(3, message);

            pstmt.executeUpdate();
            System.out.println("메시지가 성공적으로 삽입되었습니다!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ChatMessageDto> getMessages(int chatRoomId) {
        List<ChatMessageDto> messages = new ArrayList<>();
        String sql = "select sender_id, sender_name, message, sent_time from semi_chat where chat_room_id = ? order by sent_time";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, chatRoomId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int senderId = rs.getInt("sender_id");
                    String senderName = rs.getString("sender_name");
                    String message = rs.getString("message");
                    Timestamp sentTime = rs.getTimestamp("sent_time");
                    messages.add(new ChatMessageDto(senderId, senderName, message, sentTime));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }
    
    public List<Integer> getUniqueChatRoomIds() {
        List<Integer> chatRoomIds = new ArrayList<>();
        String sql = "select distinct chat_room_id from semi_chat";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                chatRoomIds.add(rs.getInt("chat_room_id"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chatRoomIds;
    }
    
    public int getMaxChatRoomId() {
        String sql = "select max(chat_room_id) from semi_chat";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
