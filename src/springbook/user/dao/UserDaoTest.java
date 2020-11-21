package springbook.user.dao;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import springbook.user.domain.User;
import org.junit.Test;

public class UserDaoTest {

	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {

		ApplicationContext context = new GenericXmlApplicationContext("springbook/user/dao/applicationContext.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("han5517");
		user.setName("JayTong");
		user.setPassword("1234");
		dao.add(user);

		User user2 = dao.get(user.getId());

		assertThat(user2.getName(), is(user.getName()));
		assertThat(user2.getPassword(), is(user.getPassword()));

	}

	public static void main(String[] args) {
		JUnitCore.main("springbook.user.dao.UserDaoTest");
	}

}
