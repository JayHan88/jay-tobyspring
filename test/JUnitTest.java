import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class JUnitTest {

	static JUnitTest testObject;

	@Test
	public void test1() {
		// sameInsteance : Creates a matcher that matches only when the examined object is the same instance as the specified target object.
		assertThat(this, is(not(sameInstance(testObject))));
		testObject = this;
	}

	@Test
	public void test2() {
		assertThat(this, is(not(sameInstance(testObject))));
		testObject = this;
	}

	@Test
	public void test3() {
		assertThat(this, is(not(sameInstance(testObject))));
		testObject = this;
	}

}
