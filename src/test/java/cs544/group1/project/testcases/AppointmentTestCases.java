package cs544.group1.project.testcases;

import cs544.group1.project.dto.AppointmentRequest;
import cs544.group1.project.dto.AppointmentResponse;
import cs544.group1.project.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest

public class AppointmentTestCases {
    @Autowired
    private AppointmentService appointmentService;

    AppointmentRequest getAppointmentRequestDTO(){
        AppointmentRequest appointmentRequest = new AppointmentRequest();
        appointmentRequest.setAppointmentDate(new Date());
        appointmentRequest.setLocationId(1);
        appointmentRequest.setUserId(1);
        return appointmentRequest;
    }
    @Test
    public void createAppointment() {
        AppointmentRequest appointmentRequest = getAppointmentRequestDTO();
        AppointmentResponse appointmentResponse = appointmentService.save(appointmentRequest);
        assertNotNull(appointmentResponse);
    }

}
