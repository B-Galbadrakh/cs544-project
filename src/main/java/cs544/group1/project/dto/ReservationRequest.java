package cs544.group1.project.dto;

import cs544.group1.project.domain.ReservationStatus;

public class ReservationRequest {

    private ReservationStatus status;

    private int consumer_id;

    private int appointment_id;

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}


	public int getConsumer_id() {
		return consumer_id;
	}

	public void setConsumer_id(int consumer_id) {
		this.consumer_id = consumer_id;
	}

	public int getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
    
    
}
