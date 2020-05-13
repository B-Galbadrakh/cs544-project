package cs544.group1.project.controller;

import cs544.group1.project.domain.Appointment;
import cs544.group1.project.service.AppointmentService;
import cs544.group1.project.service.LocationService;
import cs544.group1.project.service.UserService;
import cs544.group1.project.service.request.AppointmentRequest;
import cs544.group1.project.service.response.AppointmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping()
    public void createAppointment(@RequestBody AppointmentRequest appointmentRequest) {

    	appointmentService.save(appointmentRequest);
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
