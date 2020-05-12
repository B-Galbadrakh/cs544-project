package cs544.group1.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import cs544.group1.project.domain.Location;
import cs544.group1.project.domain.User;

@Repository
public interface LocationRepo extends JpaRepository<Location, Integer>{
		
}
