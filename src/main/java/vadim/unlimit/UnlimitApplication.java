package vadim.unlimit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class UnlimitApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnlimitApplication.class, args);
        Scanner scan = new Scanner(System.in);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Order> orders = new ArrayList<>();
            orders.add(jsonParse("{\"orderId\":2,\"amount\":1.23,\"currency\":\"USD\",\"comment\":\"оплата заказа\"}",
                    objectMapper));
            orders.add(csvParse("1,100,USD,оплата заказа"));
            for (Order order : orders) {
                conversion(order, objectMapper);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    static Order jsonParse(String json, ObjectMapper obj) throws JsonProcessingException {
        return obj.readValue(json, Order.class);
    }

    static Order csvParse(String csv) {
        String[] csvArray = csv.split(",");
        return Order.builder()
                .orderId(Long.parseLong(csvArray[0]))
                .amount(Float.parseFloat(csvArray[1]))
                .currency(csvArray[2])
                .comment(csvArray[3])
                .build();
    }

    static void conversion(Order order, ObjectMapper obj) throws JsonProcessingException {
        OrderParsing orderParsing = new OrderParsing();
            orderParsing.setId(order.getOrderId());
            //TODO спросить про наличие currency  в выходных данных
            //TODO А так же про orderId может ли он быть 0 и повторяется ли он в файлах
            orderParsing.setAmount(order.getAmount());
            orderParsing.setComment(order.getComment());
            orderParsing.setFilename("none");
            orderParsing.setLine(1);
            orderParsing.setResult("OK");
            System.out.println(obj.writeValueAsString(orderParsing));
    }
}
