package cs544.group1.project.service;

import cs544.group1.project.domain.Reservation;
import cs544.group1.project.domain.User;
import cs544.group1.project.dto.ReservationRequest;
import cs544.group1.project.dto.ReservationResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ReservationService {

	
	public ReservationResponse save(ReservationRequest reservationRequest);
	
	public List<ReservationResponse> findAll();
	
	public ReservationResponse findReservationResponseById(int reservationid);
	
	public Reservation findById(int reservationId);
	
	public ReservationResponse update(int reservationId, Reservation newReservation);
	
	public void delete(int ReservationId);
	
	public List<User> findAcceptedReservationsByDate(LocalDate date);
	
	

	public List<ReservationResponse> convertEntityListToResponsePage(List<Reservation> reservationList);

	public ReservationResponse convertEntityToResponse(Reservation reservation);

}
