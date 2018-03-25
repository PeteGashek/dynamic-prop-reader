import config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import proploader.SystemConstants;

/**
 * Created by ghost on 3/25/18.
 */
public class Application {

    /**
     * Main method of the Application
     *
     */
    public static void main(String[] args) {
//        new ClassPathXmlApplicationContext(SystemConstants.APPLICATION_CONTEXT_FILE_NAME);

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }
}