package semi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import semi.controller.*;
import semi.model.dao.MemberDao;

@Configuration
@PropertySource("classpath:/properties/db.properties")
public class MemberBeanConfig {
	@Value("${db.oracle.driver}")
	private String driver;
	@Value("${db.oracle.url}")
	private String url;
	@Value("${db.oracle.username}")
	private String username;
	@Value("${db.oracle.password}")
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

	@Bean
	public MemberDao memberDao() {
		return new MemberDao(this.dataSource());
	}

	
//	@Bean
//	public MemberController1111 memberController() {
//		return new MemberController1111();
//	}
	
	@Bean
	public LoginController loginController() {
		return new LoginController();
	}
	
	@Bean
	public RegisterController registerController() {
		return new RegisterController();
	}
	
	
	
}
