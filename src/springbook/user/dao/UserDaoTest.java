package springbook.user.dao;

import java.sql.SQLException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import springbook.user.domain.User;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		ApplicationContext context = new GenericXmlApplicationContext("springbook/user/dao/applicationContext.xml");
		// ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml", UserDao.class);
		// AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);

		UserDao dao = context.getBean("userDao", UserDao.class);
		CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);

		User user = new User();
		user.setId("kairap");
		user.setName("Jay");
		user.setPassword("1234");
		dao.add(user);
		System.out.println(user.getName() + " add complete");

		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		System.out.println(user2.getId() + " read complete");

		System.out.println("Connection counter : " + ccm.getCounter());
	}

}
