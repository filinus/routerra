
package us.filin.routerra.data.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import us.filin.routerra.data.jpa.CMLogin;
import us.filin.routerra.data.jpa.Device;

@RepositoryRestResource(excerptProjection = Projection1.class)
@CrossOrigin
public interface DeviceRepository extends JpaRepository<Device, String> {
    Device findByLoginAndDevname(CMLogin cmLogin, String devname);
}
