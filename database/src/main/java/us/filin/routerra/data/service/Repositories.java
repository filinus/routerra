package us.filin.routerra.data.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import us.filin.routerra.data.jpa.CMLogin;
import us.filin.routerra.data.jpa.Fleet;

import java.util.List;

@Component
@Transactional
@Getter()
public class Repositories {
	@Autowired
	private LocationRepository location;

	@Autowired
	private FleetRepository fleet;

	@Autowired
	private CMLoginRepository cmLogin;

	@Autowired
	private DeviceRepository device;

	public Fleet lookupFleetByCMLogin(String cmusername) {
		CMLogin entity = cmLogin.findByLogin(cmusername);
		return (entity!=null) ? entity.getFleet():null;
	}
}
