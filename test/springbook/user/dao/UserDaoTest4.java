package springbook.user.dao;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springbook.user.domain.User;

// 컨테이너 없는 DI 테스트 코드

public class UserDaoTest4 {

	private UserDao dao;
	private User user1;
	private User user2;
	private User user3;

	@Before
	public void setUp() {

		dao = new UserDao();
		DataSource dataSource = new SingleConnectionDataSource("jdbc:mysql://localhost/jay?allowPublicKeyRetrieval=true&amp;useSSL=false", "han5517", "1234", true);
		dao.setDataSource(dataSource);

		this.user1 = new User("first", "Jay", "1234");
		this.user2 = new User("second", "Jmaes", "asdf");
		this.user3 = new User("third", "Tom", "qweq");

	}

	@Test
	public void addAndGet() throws SQLException {

		dao.deleteAll();
		assertThat(dao.getCountRow(), is(0));

		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCountRow(), is(2));

		User userget1 = dao.get(user1.getId());
		assertThat(userget1.getName(), is(user1.getName()));
		assertThat(userget1.getPassword(), is(user1.getPassword()));

		User userget2 = dao.get(user2.getId());
		assertThat(userget2.getName(), is(user2.getName()));
		assertThat(userget2.getPassword(), is(user2.getPassword()));
	}

	@Test
	public void countRow() throws SQLException {

		dao.deleteAll();
		assertThat(dao.getCountRow(), is(0));
		dao.add(user1);
		assertThat(dao.getCountRow(), is(1));
		dao.add(user2);
		assertThat(dao.getCountRow(), is(2));
		dao.add(user3);
		assertThat(dao.getCountRow(), is(3));

	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException {

		dao.deleteAll();
		assertThat(dao.getCountRow(), is(0));

		dao.get("unknown_id");

	}

}
