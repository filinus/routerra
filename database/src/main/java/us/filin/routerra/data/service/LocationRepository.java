
package us.filin.routerra.data.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.filin.routerra.data.jpa.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {
}
