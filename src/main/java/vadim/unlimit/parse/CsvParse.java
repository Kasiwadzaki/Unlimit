package vadim.unlimit.parse;

import org.springframework.stereotype.Component;
import vadim.unlimit.model.OrderInput;

@Component
public class CsvParse implements FileFormatParser {
    @Override
    public OrderInput fileLineParse(String lineFromFile, long count, String fileName) {
            String[] csvArray = lineFromFile.split(",");
            return OrderInput.builder()
                    .orderId(Long.parseLong(csvArray[0]))
                    .amount(Float.parseFloat(csvArray[1]))
                    .currency(csvArray[2])
                    .comment(csvArray[3])
                    .line(count)
                    .filename(fileName)
                    .build();
    }
}
