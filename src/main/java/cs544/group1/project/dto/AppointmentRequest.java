package cs544.group1.project.dto;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class AppointmentRequest {

    @NotBlank(message = "appointmentDate is mandatory")
    private Date appointmentDate;
    @NotBlank(message = "userId is mandatory")
    private int userId;
    @NotBlank(message = "locationId is mandatory")
    private int locationId;

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
