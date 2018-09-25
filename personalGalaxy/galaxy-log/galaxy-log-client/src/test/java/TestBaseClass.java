



import junit.framework.TestCase;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public abstract class TestBaseClass extends TestCase {

	protected ClassPathXmlApplicationContext context = null;
	

	public TestBaseClass() {
		try {
			context = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/*.xml");
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
	
}
