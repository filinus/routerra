package us.filin.routerra.aggregator.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
abstract public class Timestamped extends OwntracksMessage {
    @JsonProperty(value = "tst", required = true)
    private Long timestamp; //we don't yet need to convert into a temporal type
}
