package cs544.group1.project.controller;

import cs544.group1.project.domain.Appointment;
import cs544.group1.project.dto.AppointmentRequest;
import cs544.group1.project.dto.AppointmentResponse;
import cs544.group1.project.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController extends ProjectDefaultController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping()
    public AppointmentResponse createAppointment(@RequestBody AppointmentRequest appointmentRequest) {

    	return appointmentService.save(appointmentRequest);
    }
    
    @GetMapping()
    public List<AppointmentResponse> getAppointments(){
    	return appointmentService.findAll();
    }
    
    @GetMapping("/{appointmentid}")
    public AppointmentResponse getAppointmentById(@PathVariable int appointmentid) {
    	return appointmentService.findAppointmentResponseById(appointmentid);
    }
    
    @PutMapping()
    public AppointmentResponse updateById(@RequestBody Appointment appointment) {
    	return appointmentService.update(appointment);
    }
    
    @DeleteMapping("/{appointmentid}")
    public void deleteAppointment(@PathVariable int appointmentid) {
    	appointmentService.delete(appointmentid);
    }
    
    
}
