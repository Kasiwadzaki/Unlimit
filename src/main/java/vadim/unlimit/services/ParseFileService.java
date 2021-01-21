package vadim.unlimit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vadim.unlimit.model.OrderInput;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

@Service
public class ParseFileService {

    @Autowired
    OrdersParser ordersParser;
    @Autowired
    OrdersConversion ordersConversion;

    public void run(String[] args) {

        for (int i = 0; i < args.length; i++) {
            File file = new File(args[i]);
            try {
                List<OrderInput> orderInputs = ordersParser.parse(file);
                for (OrderInput orderInput : orderInputs) {
                    ordersConversion.printResultConversion(ordersConversion.conversion(orderInput));
                }
            } catch (NoSuchFileException e) {
                System.out.println("Файл не найден");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
