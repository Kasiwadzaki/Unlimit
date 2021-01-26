package vadim.unlimit.parse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import vadim.unlimit.model.OrderInput;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CsvParse implements FileFormatParser {
    @Override
    public OrderInput fileLineParse(String lineFromFile, long count, String fileName) {
        if (!lineFromFile.isEmpty()) {
            List<String> csv = new ArrayList<>(Arrays.asList(lineFromFile.split(",")));
            if (csv.size() == 3) {
                csv.add("");
            }
            OrderInput orderInput = validationCsv(csv);
            try {
                orderInput.setOrderId(csv.get(0));
                orderInput.setAmount(csv.get(1));
                orderInput.setCurrency(csv.get(2).trim());
                orderInput.setComment(csv.get(3).trim());
                orderInput.setLine(count);
                orderInput.setFilename(fileName);
                return orderInput;
            } catch (IndexOutOfBoundsException index) {
                orderInput.setParseResult("Неправильно заполненная строка");
                orderInput.setLine(count);
                orderInput.setFilename(fileName);
                return orderInput;
            } catch (Exception e) {
                return OrderInput.builder()
                        .line(count)
                        .filename(fileName)
                        .parseResult(e.toString())
                        .build();
            }
        }
        return null;
    }

    OrderInput validationCsv(List<String> csv) {
        OrderInput orderInput = new OrderInput();
        for (String s : csv) {
            if (s.trim().equals("")) {
                orderInput.setParseResult("Неправильно заполненная строка");
            }
        }
        return orderInput;
    }
}
