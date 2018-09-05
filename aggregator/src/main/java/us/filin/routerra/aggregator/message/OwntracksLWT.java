package us.filin.routerra.aggregator.message;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OwntracksLWT extends Timestamped {

    public OwntracksLWT() {
        this.type = "lwt";
    }

}
