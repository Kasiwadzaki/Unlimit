package vadim.unlimit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vadim.unlimit.mapping.OrdersMapping;
import vadim.unlimit.model.OrderInput;
import vadim.unlimit.parse.OrdersParser;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Service
public class ParseFileService {

    @Autowired
    OrdersParser ordersParser;
    @Autowired
    OrdersMapping ordersMapping;

    public void run(String[] args) {
        Arrays.stream(args).parallel().forEach(this::attemptsToParse);
    }

    void attemptsToParse(String arg) {
        File file = new File(arg);
        try {
            List<OrderInput> orderInputs = ordersParser.parse(file);
            orderInputs.forEach(order -> ordersMapping.printResultConversion(ordersMapping.conversion(order)));
        } catch (Exception e) {
            //Так как ничео логировать нельзя здесь пусто
        }
    }
}
