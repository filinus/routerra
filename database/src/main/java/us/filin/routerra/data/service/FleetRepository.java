
package us.filin.routerra.data.service;

import org.springframework.data.jpa.repository.JpaRepository;
import us.filin.routerra.data.jpa.Fleet;

public interface FleetRepository extends JpaRepository<Fleet, String> {
}
