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
		boolean isEditValidate = validation.validation(dto); 
		return isEditValidate;
	}
	
	public boolean isEditSuccess(UserInfoDto dto) {
		boolean isEditSuccess = userdao.UpdateUserInfo(dto);
		return isEditSuccess;
	}
	
}
