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
import cs544.group1.project.service.mappers.BaseMapper;
import cs544.group1.project.util.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
	protected BaseMapper<Appointment, AppointmentResponse> responseMapper;
	
	public void save(AppointmentRequest appointmentRequest) {

		UserDTO userDTO = userService.findById(appointmentRequest.getUserId());
		User user = objectMapper.getUserEntityFromDTO(userDTO);
		Appointment appointment = new Appointment();
		appointment.setAppointmentDate(appointmentRequest.getAppointmentDate());
		appointment.setUser(user);
		Location location = locationService.findById(appointmentRequest.getLocationId());
		appointment.setLocation(location);
		//appointmentService.save(appointment);
		apointmentRepository.save(appointment);
	}
	
	public List<AppointmentResponse> findAll(){
		List<Appointment> appointments = apointmentRepository.findAll();
		List<AppointmentResponse> appointmentResponses = new ArrayList<>();
		for(Appointment app: appointments) {
			AppointmentResponse appointmentResponse = new AppointmentResponse();
			appointmentResponse.setAppointmentDate(app.getAppointmentDate());
			appointmentResponse.setCreatedDate(app.getCreatedDate());
			appointmentResponse.setId(app.getId());
			appointmentResponse.setUpdatedDate(app.getUpdatedDate());
			appointmentResponses.add(appointmentResponse);
		}
		return appointmentResponses;
	}
	
	public AppointmentResponse findAppointmentResponseById(int appointmentid) {
		Optional<Appointment> appointment = apointmentRepository.findById(appointmentid);
		AppointmentResponse appointmentResponse = new AppointmentResponse();
		if(appointment.isPresent()) {
			appointmentResponse.setAppointmentDate(appointment.get().getAppointmentDate());
			appointmentResponse.setCreatedDate(appointment.get().getCreatedDate());
			appointmentResponse.setId(appointment.get().getId());
			appointmentResponse.setUpdatedDate(appointment.get().getUpdatedDate());
		 return appointmentResponse;
		}
		else {
			return null;
		}
	}
	
	public Appointment findById(int Appointmentid) {
		Optional<Appointment> Appointment = apointmentRepository.findById(Appointmentid);
		return Appointment.isPresent() ? Appointment.get(): null; 
	}
	
	public Appointment update(int AppointmentId, Appointment newAppointment) {
		Appointment oldAppointment = findById(AppointmentId);
    	if(oldAppointment == null){
    		return null;
    	}
    	oldAppointment.setAppointmentDate(newAppointment.getAppointmentDate());
    	oldAppointment.setUpdatedDate(new Date());
    	return apointmentRepository.save(oldAppointment);
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

}
