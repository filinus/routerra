
package us.filin.routerra.data.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import us.filin.routerra.data.jpa.CMLogin;
import us.filin.routerra.data.jpa.Device;

@RepositoryRestResource(excerptProjection = Projection1.class)
@CrossOrigin
public interface DeviceRepository extends JpaRepository<Device, String> {
    Device findByLoginAndDevname(CMLogin cmLogin, String devname);

    @Modifying(clearAutomatically = true)
    @Query(
    "UPDATE Device D " +
    "SET last_location = (SELECT max(M.id) FROM Location M WHERE M.tstamp = "+
    "  (SELECT max(L.tstamp) FROM Location L WHERE L.device = :deviceId )) " +
    "WHERE D.id = :deviceId"
    )
    @Transactional
    int updateLocation(@Param("deviceId") String deviceId);
}
