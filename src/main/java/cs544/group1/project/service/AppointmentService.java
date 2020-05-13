package cs544.group1.project.service;

import cs544.group1.project.domain.Appointment;
import cs544.group1.project.dto.AppointmentRequest;
import cs544.group1.project.dto.AppointmentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppointmentService {

	public void save(AppointmentRequest appointmentRequest);
	
	public List<AppointmentResponse> findAll();
	
	public AppointmentResponse findAppointmentResponseById(int appointmentid);
	
	public Appointment findById(int Appointmentid) ;
	
	public Appointment update(int AppointmentId, Appointment newAppointment);
	
	public void delete(int AppointmentId);

	public List<AppointmentResponse> convertEntityListToResponsePage(List<Appointment> userList);

}
