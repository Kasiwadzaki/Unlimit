package vadim.unlimit.mapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vadim.unlimit.model.OrderInput;
import vadim.unlimit.model.OrderResult;

@Service
public class OrdersMapping {

    @Autowired
    ObjectMapper objectMapper;

    public OrderResult conversion(OrderInput orderInput) {
        OrderResult orderResult = new OrderResult();
        try {
            orderResult.setId(Long.parseLong(orderInput.getOrderId()));
            orderResult.setAmount(Float.parseFloat(orderInput.getAmount()));
        } catch (Exception e) {
            orderResult.setResult(e.toString());
        }
        orderResult.setCurrency(orderInput.getCurrency());
        orderResult.setComment(orderInput.getComment());
        orderResult.setLine(orderInput.getLine());
        orderResult.setFilename(orderInput.getFilename());
        if (orderResult.getResult() == null) {
            orderResult.setResult("OK");
        }
        return orderResult;
    }

    public void printResultConversion(OrderResult orderResult) {
        try {
            System.out.println(objectMapper.writeValueAsString(orderResult));
        } catch (JsonProcessingException e) {
            //Так как ничео логировать нельзя здесь пусто
        }
    }
}
