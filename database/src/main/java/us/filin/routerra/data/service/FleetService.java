package us.filin.routerra.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.filin.routerra.data.jpa.Fleet;

import java.util.List;

@Component("fleetService")
@Transactional
public class FleetService {
	private final FleetRepository fleetRepository;

	@Autowired
	public FleetService(FleetRepository fleetRepository) {
		this.fleetRepository = fleetRepository;
	}

	public List<Fleet> findAll() {
		return fleetRepository.findAll();
	}

	public Page<Fleet> findAll(Pageable pageable) {
		return fleetRepository.findAll(pageable);
	}

}
