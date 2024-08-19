package semi.model.dto;

//삭제 또는 비워둘 수 있음
public class ChatMessageDto {
	private String user;
	private String message;

	// 기본 생성자
	public ChatMessageDto() {
	}

	public ChatMessageDto(String user, String message) {
		this.user = user;
		this.message = message;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
