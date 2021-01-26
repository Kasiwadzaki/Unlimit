package vadim.unlimit.parse;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vadim.unlimit.model.OrderInput;

@Component
public class JsonParse implements FileFormatParser {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public OrderInput fileLineParse(String lineFromFile, long count, String fileName) {
        // remove [
        lineFromFile = lineFromFile.replaceAll("\\x5b", "");
        if (lineFromFile.isEmpty()) {
            return null;
        }
        try {
            OrderInput orderInput = objectMapper.readValue(lineFromFile, OrderInput.class);
            if (validationJson(orderInput)) {
                orderInput.setParseResult("Не все поля заполнены");
            }
            orderInput.setLine(count);
            orderInput.setFilename(fileName);
            return orderInput;
        } catch (JacksonException jacksonException) {
            return OrderInput.builder()
                    .line(count)
                    .filename(fileName)
                    .parseResult("Ошибка парсинга json")
                    .build();
        } catch (Exception e) {
            return OrderInput.builder()
                    .line(count)
                    .filename(fileName)
                    .parseResult(e.toString())
                    .build();
        }
    }

    boolean validationJson(OrderInput orderInput) {
        return orderInput.getOrderId() == null ||
                orderInput.getAmount() == null ||
                orderInput.getCurrency() == null ||
                orderInput.getComment() == null;
    }
}
