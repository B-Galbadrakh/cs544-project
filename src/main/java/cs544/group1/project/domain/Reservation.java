package cs544.group1.project.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
public class Reservation {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Enumerated(EnumType.STRING)
	private ReservationStatus reservationStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date reservationDate;
	
	@ManyToOne
	private User consumer;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
    private Date createdDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedDate;
    
    @ManyToOne
    private Appointment appointment;
    
    
    
    public Reservation() {
    }



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}



	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}



	public Date getReservationDate() {
		return reservationDate;
	}



	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}



	public User getConsumer() {
		return consumer;
	}



	public void setConsumer(User consumer) {
		this.consumer = consumer;
	}


	public Date getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



	public Date getUpdatedDate() {
		return updatedDate;
	}



	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}



	public Appointment getAppointment() {
		return appointment;
	}


	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	
	
	

}
