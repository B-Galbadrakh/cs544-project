package cs544.group1.project.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs544.group1.project.domain.Appointment;
import cs544.group1.project.domain.Reservation;
import cs544.group1.project.repo.AppointmentRepo;
import cs544.group1.project.service.response.AppointmentResponse;
import cs544.group1.project.service.response.ReservationResponse;

@Service
public class AppointmentService {
	
	@Autowired
	AppointmentRepo apointmentRepository;
	
	public void save(Appointment appointment) {
		
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

}
