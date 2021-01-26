package vadim.unlimit.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Float amount;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String currency;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String comment;
    private String filename;
    private long line;
    private String result;
}
