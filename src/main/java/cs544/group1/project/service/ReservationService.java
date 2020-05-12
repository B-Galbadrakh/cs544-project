package cs544.group1.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs544.group1.project.domain.Reservation;
import cs544.group1.project.repo.ReservationRepo;

@Service
public class ReservationService {
	
	@Autowired
	ReservationRepo reservationRepository;
	
	public void save(Reservation Reservation) {
		reservationRepository.save(Reservation);
	}
	
	public List<Reservation> findAll(){
		return reservationRepository.findAll();
	}
	
	public Reservation findById(int reservationId) {
		Optional<Reservation> Reservation = reservationRepository.findById(reservationId);
		return Reservation.isPresent() ? Reservation.get(): null; 
	}
	
	public Reservation update(int reservationId, Reservation newReservation) {
		Reservation oldReservation = findById(reservationId);
    	if(oldReservation == null){
    		return null;
    	}
    	oldReservation.setStatus(newReservation.getStatus());
    	return reservationRepository.save(oldReservation);
	}
	
	public void delete(int ReservationId) {
		Reservation oldReservation = findById(ReservationId);
    	if(oldReservation == null){
    		return;
    	}
    	reservationRepository.deleteById(ReservationId);
	}

}
