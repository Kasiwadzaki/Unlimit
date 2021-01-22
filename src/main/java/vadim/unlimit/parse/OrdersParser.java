package vadim.unlimit.parse;

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
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class OrdersParser {


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CsvParse csvParse;
    @Autowired
    JsonParse jsonParse;

    public List<OrderInput> parse(File file) throws IOException {
        AtomicLong count = new AtomicLong(1);
        FileFormatParser fileFormatParser = getImplementation(getFileExtension(file));
        return Files.readAllLines(Paths.get(String.valueOf(file)), StandardCharsets.UTF_8)
                .stream()
                .map(line -> fileFormatParser.fileLineParse(line, count.getAndIncrement(), file.getName()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private FileFormatParser getImplementation(String extension) {
        if (extension.equals("csv")) {
            return csvParse;
        }
        if (extension.equals("json")) {
            return jsonParse;
        }
        return null;
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }
}
