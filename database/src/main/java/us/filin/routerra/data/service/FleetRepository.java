
package us.filin.routerra.data.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.filin.routerra.data.jpa.Fleet;

@Repository
public interface FleetRepository extends JpaRepository<Fleet, Long> {
}
