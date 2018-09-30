package us.filin.routerra.aggregator.message;

import lombok.Data;

@Data
public class OwntracksTransition extends Located {

    public OwntracksTransition() {
        this.type = "transition";
    }

}
