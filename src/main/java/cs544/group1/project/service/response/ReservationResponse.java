package cs544.group1.project.service.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cs544.group1.project.domain.Appointment;
import cs544.group1.project.types.ReservationStatus;

public class ReservationResponse {

	private int id;
	
	private ReservationStatus status;
	
	private Date reservationDate;
	
    private Date createdDate;
    
    private Date updatedDate;

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
    
    
    
}
