
package us.filin.routerra.data.service;

import org.springframework.data.jpa.repository.JpaRepository;
import us.filin.routerra.data.jpa.CMLogin;

public interface CMLoginRepository extends JpaRepository<CMLogin, String> {
}
