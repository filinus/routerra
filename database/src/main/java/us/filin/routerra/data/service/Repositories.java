package us.filin.routerra.data.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.filin.routerra.data.jpa.Fleet;

import java.util.List;

@Component
@Qualifier("repositories")
@Transactional
@Getter
public class Repositories {
	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private FleetRepository fleetRepository;
}
