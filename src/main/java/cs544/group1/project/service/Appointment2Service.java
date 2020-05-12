package cs544.group1.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs544.group1.project.domain.Appointment;
import cs544.group1.project.repo.AppointmentRepo;

@Service
public class Appointment2Service {
	
	@Autowired
	AppointmentRepo apointment2Repository;
	
	public void save(Appointment appointment) {
		
		apointment2Repository.save(appointment);
	}
	
	public List<Appointment> findAll(){
		return apointment2Repository.findAll();
	}
	
	public Appointment findById(int Appointmentid) {
		Optional<Appointment> Appointment = apointment2Repository.findById(Appointmentid);
		return Appointment.isPresent() ? Appointment.get(): null; 
	}
	
	public Appointment update(int AppointmentId, Appointment newAppointment) {
		Appointment oldAppointment = findById(AppointmentId);
    	if(oldAppointment == null){
    		return null;
    	}
    	oldAppointment.setAppointmentDate(newAppointment.getAppointmentDate());
    	oldAppointment.setUpdatedDate(newAppointment.getCreatedDate());
    	return apointment2Repository.save(oldAppointment);
	}
	
	public void delete(int AppointmentId) {
		Appointment oldAppointment = findById(AppointmentId);
    	if(oldAppointment == null){
    		return;
    	}
    	apointment2Repository.deleteById(AppointmentId);
	}

}
