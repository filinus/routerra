package us.filin.routerra.aggregator.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Located extends Timestamped {

    public Located() {
        this.type = "location";
    }

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT)
    @JsonProperty(value = "lat", required = true)
    private Double latitude;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT)
    @JsonProperty(value = "lon", required = true)
    private Double longitude;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT)
    @JsonProperty(value = "cog", required = false)
    private Double course = null;


}
