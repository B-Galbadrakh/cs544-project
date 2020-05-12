package cs544.group1.project.controller;

import cs544.group1.project.domain.Reservation;
import cs544.group1.project.service.ReservationService;

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
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping()
    public void createReservation(@RequestBody Reservation reservation) {
    	reservationService.save(reservation);
    }
    
    @GetMapping()
    public List<Reservation> getReservations(){
    	return reservationService.findAll();
    }
    
    @GetMapping("/{reservationid}")
    public Reservation getReservationById(@PathVariable int Reservationid) {
    	return reservationService.findById(Reservationid);
    }
    
    @PostMapping("/{reservationid}")
    public Reservation updateById(@PathVariable int Reservationid, @RequestBody Reservation reservation) {
    	return reservationService.update(Reservationid, reservation);
    }
    
    @DeleteMapping("/{reservationid}")
    public void deleteReservation(@PathVariable int reservationid) {
    	reservationService.delete(reservationid);
    }
    
    
}
