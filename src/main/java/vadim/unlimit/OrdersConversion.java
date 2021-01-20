package vadim.unlimit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrdersConversion {

     void conversion(Order order, ObjectMapper obj) throws JsonProcessingException {
        OrderParsing orderParsing = new OrderParsing();
        orderParsing.setId(order.getOrderId());
        //TODO спросить про наличие currency  в выходных данных
        //TODO А так же про orderId может ли он быть 0 и повторяется ли он в файлах
        orderParsing.setAmount(order.getAmount());
        orderParsing.setComment(order.getComment());
        orderParsing.setFilename("none");
        orderParsing.setLine(order.getLine());
        orderParsing.setResult("OK");
        System.out.println(obj.writeValueAsString(orderParsing));
    }
}
