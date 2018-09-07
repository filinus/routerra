
package us.filin.routerra.data.service;

import org.springframework.data.jpa.repository.JpaRepository;
import us.filin.routerra.data.jpa.Location;

public interface LocationRepository extends JpaRepository<Location, String> {
}
