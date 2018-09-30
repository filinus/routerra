package us.filin.routerra.aggregator.message;

import lombok.Data;

@Data
public class OwntracksLWT extends Timestamped {

    public OwntracksLWT() {
        this.type = "lwt";
    }

}
