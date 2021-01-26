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
            if (chekingElemetsInList(csv) == null) {
                return null;
            } else {
                csv = chekingElemetsInList(csv);
            }
            OrderInput orderInput = validationCsv(csv);
            orderInput.setOrderId(csv.get(0));
            orderInput.setAmount(csv.get(1));
            orderInput.setCurrency(csv.get(2));
            orderInput.setComment(csv.get(3));
            orderInput.setLine(count);
            orderInput.setFilename(fileName);
            return orderInput;
        }
        return null;
    }

    OrderInput validationCsv(List<String> csv) {
        OrderInput orderInput = new OrderInput();
        for (String s : csv) {
            if (s.trim().equals("")) {
                orderInput.setParseResult("Некоторые столбцы пустые");
            }
        }
        return orderInput;
    }

    List<String> chekingElemetsInList(List<String> csv) {
        if (csv.size() == 3) {
            csv.add("");
        }
        if (csv.size() <= 2) {
            return null;
        }
        return csv;
    }
}
