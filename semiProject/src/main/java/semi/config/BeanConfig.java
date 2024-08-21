package semi.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import semi.controller.ChatController;
import semi.controller.ChatRoomController;
import semi.controller.HelloController;
import semi.model.dao.ChatMessageDao;
import semi.model.dao.HelloDao;
import semi.service.ChatService;
import semi.test.LoginController;

@Configuration
@PropertySource("classpath:/properties/db.properties")
public class BeanConfig {

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
	public HelloController helloController() {
		return new HelloController();
	}

	@Bean
	public HelloDao helloDao() {
		return new HelloDao(this.dataSource());
	}

	@Bean
    public ChatMessageDao chatMessageDao(DataSource dataSource) {
        return new ChatMessageDao(dataSource);
    }

    @Bean
    public ChatService chatService(ChatMessageDao chatMessageDao) {
        return new ChatService(chatMessageDao);
    }

    @Bean
    public ChatController chatController(ChatService chatService) {
        return new ChatController(chatService);
    }
    
    @Bean
    public LoginController loginController() {
        return new LoginController();
    }
    
    @Bean
    public ChatRoomController chatRoomController(ChatService chatService) {
        return new ChatRoomController(chatService);
    }
}