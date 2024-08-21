package semi.model.so;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import semi.model.User;
import semi.model.User_Post;
import semi.model.dao.UserDao;
import semi.model.dto.UserInfoDto;

public class UserService {
	
	@Autowired
	UserDao userdao;
	
	public User selectOneUser(int userId) {
		return userdao.selectOneUser(userId);
	}
	
	public List<User_Post> selectAllUserPost(int userId) {
		return userdao.selectAllUserPost(userId);
	}
	
	public boolean isEditValidate(UserInfoDto dto) {
		EditInfoValidation validation = new EditInfoValidation();
		return validation.validation(dto);
	}
	
	public boolean isEditSuccess(UserInfoDto dto) {
		return userdao.updateUserInfo(dto);
	}
	
	public boolean isDeleteSuccess(int userId) {

		return userdao.deleteUser(userId);
		
	}
	
}
