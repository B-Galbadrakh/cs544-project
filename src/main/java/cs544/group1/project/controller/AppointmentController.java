package cs544.group1.project.controller;

import cs544.group1.project.domain.Appointment;
import cs544.group1.project.domain.Reservation;
import cs544.group1.project.service.AppointmentService;
import cs544.group1.project.service.ReservationService;
import cs544.group1.project.service.response.AppointmentResponse;
import cs544.group1.project.service.response.ReservationResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping()
    public void createReservation(@RequestBody Appointment appointment) {
    	appointmentService.save(appointment);
    }
    
    @GetMapping()
    public List<AppointmentResponse> getAppointments(){
    	return appointmentService.findAll();
    }
    
    @GetMapping("/{appointmentid}")
    public AppointmentResponse getAppointmentById(@PathVariable int appointmentid) {
    	return appointmentService.findAppointmentResponseById(appointmentid);
    }
    
    @PostMapping("/{appointmentid}")
    public Appointment updateById(@PathVariable int appointmentid, @RequestBody Appointment appointment) {
    	return appointmentService.update(appointmentid, appointment);
    }
    
    @DeleteMapping("/{appointmentid}")
    public void deleteAppointment(@PathVariable int appointmentid) {
    	appointmentService.delete(appointmentid);
    }
    
    
}
