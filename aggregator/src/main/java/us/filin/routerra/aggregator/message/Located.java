package us.filin.routerra.aggregator.message;

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

    @JsonProperty(value = "lat", required = true)
    private Double latitude;

    @JsonProperty(value = "lon", required = true)
    private Double longitude;

    @JsonProperty(value = "cog", required = false)
    private Double course = null;


}
