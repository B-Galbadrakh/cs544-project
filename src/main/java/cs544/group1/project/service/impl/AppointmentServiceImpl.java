package cs544.group1.project.service.impl;

import cs544.group1.project.domain.Appointment;
import cs544.group1.project.domain.Location;
import cs544.group1.project.domain.User;
import cs544.group1.project.dto.AppointmentRequest;
import cs544.group1.project.dto.AppointmentResponse;
import cs544.group1.project.dto.UserDTO;
import cs544.group1.project.repo.AppointmentRepo;
import cs544.group1.project.service.AppointmentService;
import cs544.group1.project.service.UserService;
import cs544.group1.project.service.mappers.AppointmentResponseMapper;
import cs544.group1.project.util.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
    AppointmentRepo apointmentRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private LocationServiceImpl locationService;

	@Autowired
	CustomObjectMapper objectMapper;

	@Autowired
	protected AppointmentResponseMapper responseMapper;
	
	public void save(AppointmentRequest appointmentRequest) {

		UserDTO userDTO = userService.findById(appointmentRequest.getUserId());
		User user = objectMapper.getUserEntityFromDTO(userDTO);
		Appointment appointment = new Appointment();
		appointment.setAppointmentDate(appointmentRequest.getAppointmentDate());
		appointment.setUser(user);
		Location location = locationService.findById(appointmentRequest.getLocationId());
		appointment.setLocation(location);
		apointmentRepository.save(appointment);
	}
	
	public List<AppointmentResponse> findAll(){
		List<Appointment> appointments = apointmentRepository.findAll();
		return convertEntityListToResponsePage(appointments);
	}
	
	public AppointmentResponse findAppointmentResponseById(int appointmentid) {
		Optional<Appointment> appointment = apointmentRepository.findById(appointmentid);
		if(appointment.isPresent()) {
			return convertEntityToResponse(appointment.get());
		}
		else {
			return null;
		}
	}
	
	public Appointment findById(int Appointmentid) {
		Optional<Appointment> Appointment = apointmentRepository.findById(Appointmentid);
		return Appointment.isPresent() ? Appointment.get(): null; 
	}
	
	public AppointmentResponse update(Appointment newAppointment) {
		Appointment oldAppointment = findById(newAppointment.getId());
    	if(oldAppointment == null){
    		return null;
    	}
    	oldAppointment.setAppointmentDate(newAppointment.getAppointmentDate());
    	oldAppointment.setUpdatedDate(new Date());
    	apointmentRepository.save(oldAppointment);
    	return convertEntityToResponse(oldAppointment);
	}
	
	public void delete(int AppointmentId) {
		Appointment oldAppointment = findById(AppointmentId);
    	if(oldAppointment == null){
    		return;
    	}
    	apointmentRepository.deleteById(AppointmentId);
	}

	@Override
	public List<AppointmentResponse> convertEntityListToResponsePage(List<Appointment> appointmentList) {
		if(null == appointmentList){
			return null;
		}
		else {
			return appointmentList.stream()
					.map(responseMapper::map)
					.collect(Collectors.toList());
		}
	}

	@Override
	public AppointmentResponse convertEntityToResponse(Appointment appointment) {
		return responseMapper.map(appointment);
	}

}
