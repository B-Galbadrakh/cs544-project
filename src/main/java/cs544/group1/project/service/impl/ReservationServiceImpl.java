package cs544.group1.project.service.impl;

import cs544.group1.project.domain.Appointment;
import cs544.group1.project.domain.Reservation;
import cs544.group1.project.domain.User;
import cs544.group1.project.dto.AppointmentResponse;
import cs544.group1.project.dto.ReservationRequest;
import cs544.group1.project.dto.ReservationResponse;
import cs544.group1.project.dto.UserDTO;
import cs544.group1.project.repo.ReservationRepo;
import cs544.group1.project.service.ReservationService;
import cs544.group1.project.service.UserService;
import cs544.group1.project.util.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
    ReservationRepo reservationRepository;
	
	@Autowired
    UserService userService;
	
	@Autowired
	AppointmentServiceImpl appointmentService;

	@Autowired
	CustomObjectMapper objectMapper;
	
	public void save(ReservationRequest reservationRequest) {
		Reservation reservation = new Reservation();
		reservation.setStatus(reservationRequest.getStatus());
		
		UserDTO userDTO = userService.findById(reservationRequest.getConsumer_id());
		System.out.println(userDTO.getFirstName());
		
		
		User user = objectMapper.getUserEntityFromDTO(userDTO);
		Appointment appointment = appointmentService.findById(reservationRequest.getAppointment_id());
		
		reservation.setConsumer(user);
		reservation.setAppointment(appointment);
		
		reservationRepository.save(reservation);
	}
	
	public List<ReservationResponse> findAll(){
		List<Reservation> reservations = reservationRepository.findAll();
		List<ReservationResponse> reservationResponses = new ArrayList<>();
		for(Reservation res: reservations) {
			ReservationResponse reservationResponse = new ReservationResponse();
			reservationResponse.setCreatedDate(res.getCreatedDate());
			reservationResponse.setId(res.getId());
			reservationResponse.setReservationDate(res.getReservationDate());
			reservationResponse.setStatus(res.getStatus());
			reservationResponse.setUpdatedDate(res.getUpdatedDate());
			reservationResponses.add(reservationResponse);
		}
		return reservationResponses;
	}
	
	public ReservationResponse findReservationResponseById(int reservationid) {
		Optional<Reservation> reservation = reservationRepository.findById(reservationid);
		ReservationResponse reservationResponse = new ReservationResponse();
		if(reservation.isPresent()) {
			reservationResponse.setCreatedDate(reservation.get().getCreatedDate());
			reservationResponse.setId(reservation.get().getId());
			reservationResponse.setReservationDate(reservation.get().getReservationDate());
			reservationResponse.setStatus(reservation.get().getStatus());
			reservationResponse.setUpdatedDate(reservation.get().getUpdatedDate());
		 return reservationResponse;
		}
		else {
			return null;
		}
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

	@Override
	public List<User> findAcceptedReservationsByDate(LocalDate date) {
		return reservationRepository.findAcceptedReservationsByAppointmentDate(date);
	}
	
	
	

}
