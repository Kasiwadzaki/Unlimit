package vadim.unlimit.parse;

import com.fasterxml.jackson.core.JsonProcessingException;
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
        try {
            OrderInput orderInput = objectMapper.readValue(lineFromFile, OrderInput.class);
            orderInput.setLine(count);
            orderInput.setFilename(fileName);
            return orderInput;
        } catch (JsonProcessingException e) {
            //Так как ничео логировать нельзя здесь пусто
        }
        return null;
    }
}
