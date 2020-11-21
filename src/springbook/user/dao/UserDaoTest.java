package springbook.user.dao;

import java.sql.SQLException;
import javax.swing.plaf.synth.SynthMenuBarUI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbook.user.domain.User;

public class UserDaoTest {
	// 테스트용 main() method
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		AnnotationConfigApplicationContext conetxt = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
		UserDao dao = conetxt.getBean("userDao", UserDao.class);

		CountingConnectionMaker ccm = conetxt.getBean("connectionMaker", CountingConnectionMaker.class);
		System.out.println("Connection counter : " + ccm.getCounter());

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
	}

}
