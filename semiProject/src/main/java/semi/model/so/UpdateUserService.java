package semi.model.so;

import semi.model.dao.UserDao;
import semi.model.dto.UserInfoDto;

public class UpdateUserService {

	public boolean EditUserInfo(UserDao dao, UserInfoDto dto) {
		return dao.UpdateUserInfo(dto);
	}
	
}
