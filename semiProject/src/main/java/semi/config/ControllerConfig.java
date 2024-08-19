package semi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import semi.controller.LoginController;
import semi.controller.RegisterController;




@Configuration
public class ControllerConfig {
	
	@Bean
	public LoginController loginController() {
		return new LoginController();
	}
	
	@Bean
	public semi.controller.RegisterController registerController() {
		return new RegisterController();
	}
	
}
