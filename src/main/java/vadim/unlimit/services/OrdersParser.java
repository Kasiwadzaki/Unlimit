package vadim.unlimit.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vadim.unlimit.model.OrderInput;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class OrdersParser {


    @Autowired
    ObjectMapper objectMapper;

    List<OrderInput> parse(File file) throws IOException {
        if (getFileExtension(file).equals("csv")) {
            AtomicLong count = new AtomicLong(1);
            return Files.readAllLines(Paths.get(String.valueOf(file)), StandardCharsets.UTF_8)
                    .stream()
                    .map(csv -> csvParse(csv, count.getAndIncrement(), file.getName()))
                    .collect(Collectors.toList());
        }
        if (getFileExtension(file).equals("json")) {
            AtomicLong count = new AtomicLong(1);
            return Files.readAllLines(Paths.get(String.valueOf(file)), StandardCharsets.UTF_8)
                    .stream()
                    .map((String json) -> jsonParse(json, count.getAndIncrement(), file.getName()))
                    .collect(Collectors.toList());
        } else {
            System.out.println("Такого файла не существует или он не поддерживается");
            return null;
        }
    }

    OrderInput jsonParse(String json, long count, String fileName) {
        // remove [
        json = json.replaceAll("\\x5b", "");
        try {
            OrderInput orderInput = objectMapper.readValue(json, OrderInput.class);
            orderInput.setLine(count);
            orderInput.setFilename(fileName);
            return orderInput;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    OrderInput csvParse(String csv, long count, String fileName) {
        String[] csvArray = csv.split(",");
        return OrderInput.builder()
                .orderId(Long.parseLong(csvArray[0]))
                .amount(Float.parseFloat(csvArray[1]))
                .currency(csvArray[2])
                .comment(csvArray[3])
                .line(count)
                .filename(fileName)
                .build();
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }
}
