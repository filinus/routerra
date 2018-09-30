package us.filin.routerra.aggregator.message;

import lombok.Data;

@Data
public class OwntracksLocation extends Located {

    public OwntracksLocation() {
        this.type = "location";
    }
}
