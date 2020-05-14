package cs544.group1.project.repo;

import cs544.group1.project.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface LocationRepo extends JpaRepository<Location, Integer>{
		
}
