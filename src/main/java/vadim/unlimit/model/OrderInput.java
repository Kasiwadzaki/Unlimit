package vadim.unlimit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderInput {
    private Long orderId;
    private Float amount;
    private String currency;
    private String comment;
    private long line;
    private String filename;
}



