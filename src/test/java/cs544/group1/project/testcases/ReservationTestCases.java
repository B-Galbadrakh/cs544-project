package cs544.group1.project.testcases;

import cs544.group1.project.domain.ReservationStatus;
import cs544.group1.project.dto.ReservationRequest;
import cs544.group1.project.dto.ReservationResponse;
import cs544.group1.project.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class ReservationTestCases {
    @Autowired
    private ReservationService reservationService;

    ReservationRequest getReservationRequestDTO(){
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setAppointment_id(1000);
        reservationRequest.setConsumer_id(1);
        reservationRequest.setStatus(ReservationStatus.ACCEPTED);
        return reservationRequest;
    }
    @Test
    public void createReservation() {
        ReservationRequest reservationRequest = getReservationRequestDTO();
        ReservationResponse reservationResponse = reservationService.save(reservationRequest);
        assertNotNull(reservationResponse);
    }

}
