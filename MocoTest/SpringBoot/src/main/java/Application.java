import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication        ////等于@Configuration,@EnableAutoConfiguration,@ComponentScan
@ComponentScan("com.course.server")         //扫描
public class Application{

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
