import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

import java.util.HashSet;
import java.util.Set;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class JUnitTest2 {

	static Set<JUnitTest2> testObjects = new HashSet<JUnitTest2>();

	@Test
	public void test1() {
		assertThat(testObjects, not(this));
		testObjects.add(this);
	}

	@Test
	public void test2() {
		assertThat(testObjects, not(this));
		testObjects.add(this);
		}

	@Test
	public void test3() {
		assertThat(testObjects, not(this));
		testObjects.add(this);
	}

}
