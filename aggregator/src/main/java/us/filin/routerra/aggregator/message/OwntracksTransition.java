package us.filin.routerra.aggregator.message;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OwntracksTransition extends Located {

    public OwntracksTransition() {
        this.type = "transition";
    }

}
