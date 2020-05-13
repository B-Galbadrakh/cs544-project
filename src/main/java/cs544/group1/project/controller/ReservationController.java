package cs544.group1.project.controller;

import cs544.group1.project.domain.Reservation;
import cs544.group1.project.domain.User;
import cs544.group1.project.dto.ReservationRequest;
import cs544.group1.project.dto.ReservationResponse;
import cs544.group1.project.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping()
    public ReservationResponse createReservation(@RequestBody ReservationRequest reservationRequest) {
    	return reservationService.save(reservationRequest);
    }
    
    @GetMapping()
    public List<ReservationResponse> getReservations(){
    	return reservationService.findAll();
    }
    
    @GetMapping("/{reservationid}")
    public ReservationResponse getReservationById(@PathVariable int reservationid) {
    	return reservationService.findReservationResponseById(reservationid);
    }
    
    @PutMapping()
    public ReservationResponse updateById(@RequestBody Reservation reservation) {
    	return reservationService.update(reservation);
    }
    
    @DeleteMapping("/{reservationid}")
    public void deleteReservation(@PathVariable int reservationid) {
    	reservationService.delete(reservationid);
    }
    
    @GetMapping(value = "/scheduled")
    public List<User> triggerScheduled() throws ParseException {
    	
    	LocalDate currentDate = LocalDate.now();
    	System.out.println("CURRENTDATE: "+currentDate);
		
    	return reservationService.findAcceptedReservationsByDate(currentDate);
    	
    }
    
    
}
