package vadim.unlimit.main;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import vadim.unlimit.services.ParseFileService;

@SpringBootApplication
@ComponentScan("vadim.unlimit")
public class UnlimitApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UnlimitApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        ApplicationContext context = app.run(args);
        context.getBean(ParseFileService.class).run(args);
    }

}


