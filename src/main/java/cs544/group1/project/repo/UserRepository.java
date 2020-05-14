package cs544.group1.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cs544.group1.project.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT u from User u left join fetch u.role where u.email = :email")
    List<User> findByEmail(String email);

}
