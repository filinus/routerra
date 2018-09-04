package us.filin.routerra.aggregator;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class OwntracksLocation extends OwntracksMessage {

    public OwntracksLocation() {
        this.type = "location";
    }

    @JsonProperty(value = "tst", required = true)
    private Long timestamp; //we don't yet need to convert into a temporal type

    @JsonProperty(value = "lat", required = true)
    private Double latitude;

    @JsonProperty(value = "lon", required = true)
    private Double longitude;

    @JsonProperty(value = "cog", required = false)
    private Double course = null;


}
