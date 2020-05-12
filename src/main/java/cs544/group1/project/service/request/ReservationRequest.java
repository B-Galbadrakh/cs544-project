package cs544.group1.project.service.request;

import cs544.group1.project.domain.Appointment;
import cs544.group1.project.domain.User;
import cs544.group1.project.types.ReservationStatus;

import javax.persistence.*;
import java.util.Date;

public class ReservationRequest {
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationDate;

    @ManyToOne
    @JoinColumn(name="consumer_id")
    private User consumer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="appointment_id")
    private Appointment appointment;
}
