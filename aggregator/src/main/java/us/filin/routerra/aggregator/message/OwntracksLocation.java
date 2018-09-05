package us.filin.routerra.aggregator.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OwntracksLocation extends Located {

    public OwntracksLocation() {
        this.type = "location";
    }
}
