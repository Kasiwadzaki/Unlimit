package vadim.unlimit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersParser {

    long csvFileLineCount;
    long jsonFileLineCount;

    List<Order> parse(String filename) throws IOException {
        if (filename.equals("C:\\Users\\kasiw\\IdeaProjects\\unlimit\\src\\main\\resources\\orders1.csv")) {
            return Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8)
                    .stream()
                    .map(this::csvParse)
                    .collect(Collectors.toList());
        }
        if (filename.equals("C:\\Users\\kasiw\\IdeaProjects\\unlimit\\src\\main\\resources\\orders2.json")) {
            ObjectMapper obj = new ObjectMapper();
            return Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8)
                    .stream()
                    .map(l -> {
                        try {
                            return jsonParse(l, obj);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .collect(Collectors.toList());
        } else {
            System.out.println("Такого файла не существует");
        }
        return null;
    }

    Order jsonParse(String json, ObjectMapper obj) throws JsonProcessingException {
        jsonFileLineCount++;
        json = json.replaceAll("\\x5b", "");
        Order order = obj.readValue(json, Order.class);
        order.setLine(jsonFileLineCount);
        return order;
    }

    Order csvParse(String csv) {
        csvFileLineCount++;
        String[] csvArray = csv.split(",");
        return Order.builder()
                .orderId(Long.parseLong(csvArray[0]))
                .amount(Float.parseFloat(csvArray[1]))
                .currency(csvArray[2])
                .comment(csvArray[3])
                .line(csvFileLineCount)
                .build();
    }
}
