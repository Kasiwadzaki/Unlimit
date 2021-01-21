package vadim.unlimit.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vadim.unlimit.model.OrderInput;
import vadim.unlimit.model.OrderResult;

@Service
public class OrdersConversion {

    @Autowired
    ObjectMapper objectMapper;

    OrderResult conversion(OrderInput orderInput) {
        return OrderResult.builder()
                .id(orderInput.getOrderId())
                .amount(orderInput.getAmount())
                .currency(orderInput.getCurrency())
                .comment(orderInput.getComment())
                .filename(orderInput.getFilename())
                .line(orderInput.getLine())
                .result("OK")
                .build();
    }

    void printResultConversion(OrderResult orderResult) {
        try {
            System.out.println(objectMapper.writeValueAsString(orderResult));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}