package springbook.user.dao;

import java.sql.SQLException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import springbook.user.domain.User;

public class OldUserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		ApplicationContext context = new GenericXmlApplicationContext("springbook/user/dao/applicationContext.xml");
		// ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml", UserDao.class);
		// AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);

		UserDao2 dao = context.getBean("userDao", UserDao2.class);
		CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);

		User user = new User();
		user.setId("kairap");
		user.setName("Jay");
		user.setPassword("1234");
		dao.add(user);
		System.out.println("ID : " + user.getId() + " --> add complete");
		System.out.println("------------------------------------------");

		User user2 = dao.get(user.getId());
		System.out.println("ID : " + user2.getId() + " --> read complete");
		System.out.println("Name : " + user2.getName());
		System.out.println("Passworkd : " + user2.getPassword());
		System.out.println("------------------------------------------");

		if (!user.getName().equals(user2.getName())) {
			System.out.println("Test Fail. Different Name");
		} else if (!user.getPassword().equals(user2.getPassword())) {
			System.out.println("Test Fail. Different Password");
		} else {
			System.out.println("Test Success.");
		}

		System.out.println("Connection counter : " + ccm.getCounter());
	}

}
