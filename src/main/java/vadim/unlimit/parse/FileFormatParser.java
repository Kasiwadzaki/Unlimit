package vadim.unlimit.parse;


import vadim.unlimit.model.OrderInput;

public interface FileFormatParser {
    public OrderInput fileLineParse(String lineFromFile, long count, String fileName);
}
