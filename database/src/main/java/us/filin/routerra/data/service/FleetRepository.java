
package us.filin.routerra.data.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import us.filin.routerra.data.jpa.Fleet;

import java.util.List;

interface FleetRepository extends Repository<Fleet, Long> {
	List<Fleet> findAll();

	Page<Fleet> findAll(Pageable pageable);
}
