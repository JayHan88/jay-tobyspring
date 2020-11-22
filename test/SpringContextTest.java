import java.util.HashSet;
import java.util.Set;
import javax.swing.Spring;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/springbook/user/dao/junit.xml")
public class SpringContextTest {

	@Autowired
	private ApplicationContext context;

	static Set<SpringContextTest> testObjects = new HashSet<SpringContextTest>();
	static ApplicationContext contextObject = null;

	@Test
	public void test1() {
		assertThat(testObjects, not(this));
		testObjects.add(this);

		assertThat(contextObject == null || contextObject == this.context, is(true));
		contextObject = this.context;
	}

	@Test
	public void test2() {
		assertThat(testObjects, not(this));
		testObjects.add(this);

		assertTrue(contextObject == null || contextObject == this.context);
		contextObject = this.context;
	}

	@Test
	public void test3() {
		assertThat(testObjects, not(this));
		testObjects.add(this);

		assertThat(contextObject, either(is(nullValue())).or(is(this.context)));
		contextObject = this.context;
	}


}
