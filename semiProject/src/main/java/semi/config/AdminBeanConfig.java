package semi.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource("classpath:/properties/db.properties")
@ComponentScan(basePackages = { "semi" })
public class AdminBeanConfig {
	@Value("${db.oracle.driver}")
	private String driver;
	@Value("${db.oracle.url}")
	private String url;
	@Value("${db.oracle.username}")
	private String username;
	@Value("${db.oracle.password}")
	private String password;

	@Bean
	@Autowired
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
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager dstm = new DataSourceTransactionManager();
		dstm.setDataSource(dataSource());

		return dstm;
	}
}