package cs544.group1.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cs544.group1.project.domain.Appointment;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer>{

}
