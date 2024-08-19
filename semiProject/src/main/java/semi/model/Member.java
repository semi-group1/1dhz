package semi.model;

import java.time.LocalDateTime;

public class Member {
		private int user_id;
		private String email;
		private String password;
		private String mgr;
		private LocalDateTime registerdatetime;

		public Member(String email, String password, String hiredate, LocalDateTime registerdatetime) {
			this.email = email;
			this.password = password;
			this.registerdatetime = registerdatetime;
		}

		public int user_id() {
			return user_id;
		}

		public void setEmpno(int user_id) {
			this.user_id = user_id;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getPassword() {
			return password;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
		

	}
