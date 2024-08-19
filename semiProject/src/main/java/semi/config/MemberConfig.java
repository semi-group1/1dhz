package semi.config;

import org.springframework.context.annotation.Bean;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import semi.controller.*;
import semi.model.dao.MemberDao;

@Configuration
@PropertySource("classpath:/properties/db.properties")
@ComponentScan(basePackages = {"semi"})
public class MemberConfig {
	@Value("${db.orcacle.driver}")
	private String driver;
	@Value("${db.orcacle.url}")
	private String url;
	@Value("${db.orcacle.username}")
	private String username;
	@Value("${db.orcacle.password}")
	private String password;
	
	@Bean
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setInitialSize(5);
		ds.setMinIdle(5);
		ds.setMaxIdle(10);
		ds.setMinEvictableIdleTimeMillis(1000 * 60 * 3);
		ds.setTimeBetweenEvictionRunsMillis(1000 * 10);
		
		return ds;
	}
	
	
//	@Bean
//	public LoginController loginController() {
//		return new LoginController();
//	}
//	
//	@Bean
//	public RegisterController registerControoler() {
//		return new RegisterController();
//	}
	
	@Bean
	public MemberController memberController() {
		return new MemberController();
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
}
