package cs544.group1.project.service;

import cs544.group1.project.domain.Reservation;
import cs544.group1.project.dto.ReservationRequest;
import cs544.group1.project.dto.ReservationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {

	
	public void save(ReservationRequest reservationRequest);
	
	public List<ReservationResponse> findAll();
	
	public ReservationResponse findReservationResponseById(int reservationid);
	
	public Reservation findById(int reservationId);
	
	public ReservationResponse update(Reservation newReservation);
	
	public void delete(int ReservationId);

	public List<ReservationResponse> convertEntityListToResponsePage(List<Reservation> reservationList);

	public ReservationResponse convertEntityToResponse(Reservation reservation);

}
