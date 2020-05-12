package cs544.group1.project.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class UserRole {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToMany(mappedBy = "role")
	private List<User> user;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    
    
    public UserRole() {
    	this.createdDate = new Date();
		this.updatedDate = new Date();
    }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<User> getUser() {
		return user;
	}


	public void setUser(List<User> user) {
		this.user = user;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
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
