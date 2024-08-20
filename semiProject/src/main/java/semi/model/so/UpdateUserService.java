package semi.model.so;

import semi.model.dao.UserDao;
import semi.model.dto.UserInfoDto;

public class UpdateUserService {
	
	public boolean EditUserInfo(UserInfoDto dto) {
		UserDao dao = new UserDao();
		return dao.UpdateUserInfo(dto);
	}
	
}
