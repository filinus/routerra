package us.filin.routerra.aggregator.message;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

/**
 * https://stackoverflow.com/questions/44122782/jackson-deserialize-based-on-type
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "_type",
        visible = true,
        defaultImpl = OwntracksAnother.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OwntracksLocation.class, name = "location"),
        @JsonSubTypes.Type(value = OwntracksTransition.class, name = "transition"),
        @JsonSubTypes.Type(value = OwntracksLWT.class, name = "lwt")
})
@Data
abstract public class OwntracksMessage {
    @JsonTypeId
    @JsonProperty("_type")
    protected String type;
}