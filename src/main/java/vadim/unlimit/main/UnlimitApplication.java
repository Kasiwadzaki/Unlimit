package vadim.unlimit.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import vadim.unlimit.services.ParseFileService;

@SpringBootApplication
@ComponentScan("vadim.unlimit")
public class UnlimitApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(UnlimitApplication.class, args);
        context.getBean(ParseFileService.class).run(args);
    }

}


