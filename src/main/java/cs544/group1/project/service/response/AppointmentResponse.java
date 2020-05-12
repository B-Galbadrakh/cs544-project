package cs544.group1.project.service.response;

import java.util.Date;
import java.util.List;


import cs544.group1.project.domain.Location;
import cs544.group1.project.domain.Reservation;
import cs544.group1.project.domain.User;

public class AppointmentResponse {
	
	private int id;
	
	private Date appointmentDate;
	
    private Date createdDate;
    
    private Date updatedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
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
