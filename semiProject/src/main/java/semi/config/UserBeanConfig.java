package semi.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import semi.controller.*;
import semi.model.dao.HelloDao;
import semi.model.dao.UserDao;
import semi.model.so.UserService;

@Configuration
@PropertySource("classpath:/properties/db.properties")
public class UserBeanConfig {

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
	public UserController userController() {
		return new UserController();
	}
	
	@Bean
	public UserService userService() {
		return new UserService();
	}
	
	@Bean
	public UserDao userDao() {
		return new UserDao(this.dataSource());
	}
	
}