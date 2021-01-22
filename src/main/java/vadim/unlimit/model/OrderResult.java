package vadim.unlimit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResult {
    private Long id;
    private Float amount;
    private String currency;
    private String comment;
    private String filename;
    private long line;
    private String result;
}
