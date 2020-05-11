package cs544.group1.project.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Column;

@Entity
public class Appointment {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date appointmentDate;
	
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    
    @ManyToOne
    private User user;
    
    @OneToMany
    private List<Reservation> reservations;
    
    
    @AttributeOverrides({
        @AttributeOverride(name="createdDate",
                           column=@Column(name="LOC_CREATEDDATE")),
        @AttributeOverride(name="updatedDate",
                           column=@Column(name="LOC_UPDATEDDATE"))
    })
    @Embedded
    private Location location;
    
    
    
    public Appointment() {
    	this.createdDate = new Date();
		this.updatedDate = new Date();
    }



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



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public List<Reservation> getReservations() {
		return reservations;
	}



	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	};

}
