
package us.filin.routerra.data.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import us.filin.routerra.data.jpa.Location;

@RepositoryRestResource
@CrossOrigin
public interface LocationRepository extends JpaRepository<Location, String> {
}
