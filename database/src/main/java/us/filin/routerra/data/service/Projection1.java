package us.filin.routerra.data.service;

import org.springframework.data.rest.core.config.Projection;
import us.filin.routerra.data.jpa.Device;
import us.filin.routerra.data.jpa.Fleet;
import us.filin.routerra.data.jpa.Location;

@Projection(name = "projection1", types = {Device.class, Location.class})
public interface Projection1 {
    String getId();
    String getDevname();
    Fleet getFleet();
    Location getLastLocation();
}
