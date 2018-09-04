package us.filin.routerra.aggregator;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

/**
 * https://stackoverflow.com/questions/44122782/jackson-deserialize-based-on-type
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "_type",
        visible = true,
        defaultImpl = OwntracksMessage.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OwntracksLocation.class, name = "location"),
        @JsonSubTypes.Type(value = OwntracksLocation.class, name = "transition"),
        @JsonSubTypes.Type(value = OwntracksLocation.class, name = "lwt")
})
@Getter
public class OwntracksMessage {
    @JsonTypeId
    @JsonProperty("_type")
    protected String type;
}