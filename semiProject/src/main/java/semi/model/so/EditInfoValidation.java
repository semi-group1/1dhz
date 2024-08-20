package semi.model.so;

import semi.model.dto.UserInfoDto;

public class EditInfoValidation {
	
	public boolean validation(UserInfoDto dto) {
		if(!dto.getPasswd().equals(dto.getPasswdCheck())) {
			return false;
		}
		
		return true;
	}
	
}
