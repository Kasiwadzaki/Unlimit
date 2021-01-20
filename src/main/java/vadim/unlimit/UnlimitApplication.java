package vadim.unlimit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

@SpringBootApplication
public class UnlimitApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnlimitApplication.class, args);
        OrdersParser ordersParser = new OrdersParser();
        OrdersConversion ordersConversion = new OrdersConversion();
        try {
            List<Order> orders = ordersParser.parse("C:\\Users\\kasiw\\IdeaProjects\\unlimit\\src\\main\\resources\\orders2.json");
            ObjectMapper obj = new ObjectMapper();
            for (Order order : orders) {
                ordersConversion.conversion(order, obj);
            }
        } catch (NoSuchFileException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
