package springbook.user.dao;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class CountingDaoFactory2 {

	@Bean
	public UserDao userDao2() {
		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource());
		return userDao2();
	}

	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost/jay?allowPublicKeyRetrieval=true&useSSL=false");
		dataSource.setUsername("han5517");
		dataSource.setPassword("1234");

		return dataSource;
	}

}
