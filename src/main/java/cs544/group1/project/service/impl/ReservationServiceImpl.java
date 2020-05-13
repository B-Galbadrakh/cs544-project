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
import cs544.group1.project.service.mappers.ReservationResponseMapper;
import cs544.group1.project.util.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	@Autowired
	protected ReservationResponseMapper responseMapper;
	
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
		return convertEntityListToResponsePage(reservations);
	}
	
	public ReservationResponse findReservationResponseById(int reservationid) {
		Optional<Reservation> reservation = reservationRepository.findById(reservationid);

		if(reservation.isPresent()) {
			return convertEntityToResponse(reservation.get());
		}
		else{
			return null;
		}
	}
	
	public Reservation findById(int reservationId) {
		Optional<Reservation> Reservation = reservationRepository.findById(reservationId);
		return Reservation.isPresent() ? Reservation.get(): null; 
	}
	
	public ReservationResponse update(Reservation newReservation) {
		Reservation oldReservation = findById(newReservation.getId());
    	if(oldReservation == null){
    		return null;
    	}
    	oldReservation.setStatus(newReservation.getStatus());
    	oldReservation.setReservationDate(newReservation.getReservationDate());
    	reservationRepository.save(oldReservation);
    	return convertEntityToResponse(oldReservation);
	}
	
	public void delete(int ReservationId) {
		Reservation oldReservation = findById(ReservationId);
    	if(oldReservation == null){
    		return;
    	}
    	reservationRepository.deleteById(ReservationId);
	}

	@Override
	public List<ReservationResponse> convertEntityListToResponsePage(List<Reservation> reservationList) {
		if(null == reservationList){
			return null;
		}
		else {
			return reservationList.stream()
					.map(responseMapper::map)
					.collect(Collectors.toList());
		}
	}

	@Override
	public ReservationResponse convertEntityToResponse(Reservation reservation) {
		return responseMapper.map(reservation);
	}

	public List<User> findAcceptedReservationsByDate(LocalDate date) {
		return reservationRepository.findAcceptedReservationsByAppointmentDate(date);
	}
}
