package us.filin.routerra.aggregator.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
abstract public class Timestamped extends OwntracksMessage {
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    @JsonProperty(value = "tst", required = true)
    private Long timestamp; //we don't yet need to convert into a temporal type

    public Date getDate() {
        return new Date(1000*((timestamp == null) ? 0L : timestamp));
    }
}
