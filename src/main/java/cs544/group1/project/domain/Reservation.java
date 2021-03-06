package cs544.group1.project.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Reservation {

	@Id
	@GeneratedValue
	private int id;

	@Enumerated(EnumType.STRING)
	private ReservationStatus status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date reservationDate;

	@ManyToOne
	@JoinColumn(name="consumer_id")
	private User consumer;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedDate;

	@ManyToOne
	@JoinColumn(name="appointment_id")
	private Appointment appointment;



	public Reservation() {

	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public ReservationStatus getStatus() {
		return status;
	}



	public void setStatus(ReservationStatus status) {
		this.status = status;
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
