
package us.filin.routerra.data.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import us.filin.routerra.data.jpa.CMLogin;

@RepositoryRestResource
@CrossOrigin
public interface CMLoginRepository extends JpaRepository<CMLogin, String> {
}
