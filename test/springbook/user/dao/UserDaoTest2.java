package springbook.user.dao;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springbook.user.domain.User;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class) // spring의 test context framework의 JUnit 확장기능 지정
@ContextConfiguration(locations = "/springbook/user/dao/applicationContext.xml") // test context가 자동으로 만들어줄 application context 위치 지정
// 스프링 테스트 컨텍스트 프레임워크 적용
public class UserDaoTest2 {

	@Autowired
	private ApplicationContext context;

	//fixture
	@Autowired
	UserDao dao;

	private User user1;
	private User user2;
	private User user3;

	@Before
	public void setUp() {

		// this.dao = context.getBean("userDao", UserDao.class);
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
