package cs544.group1.project.repo;

import cs544.group1.project.domain.Reservation;
import cs544.group1.project.domain.User;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ReservationRepo extends JpaRepository<Reservation, Integer> {
	
	@org.springframework.data.jpa.repository.Query(value = "SELECT consumer FROM Reservation re WHERE date(re.appointment.date) >= date(:date) AND date(re.appointment.date) <= date(:date)  AND re.status = 'ACCEPTED'")
	public List<User> findAcceptedReservationsByAppointmentDate(@Param("date") LocalDate date);
	
	
}
