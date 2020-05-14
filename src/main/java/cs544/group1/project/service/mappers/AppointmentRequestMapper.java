package cs544.group1.project.service.mappers;

import cs544.group1.project.domain.Appointment;
import cs544.group1.project.domain.Location;
import cs544.group1.project.domain.User;
import cs544.group1.project.dto.AppointmentRequest;
import cs544.group1.project.dto.UserDTO;
import cs544.group1.project.service.LocationService;
import cs544.group1.project.service.UserService;
import cs544.group1.project.util.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentRequestMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private LocationService locationService;

    @Autowired
    CustomObjectMapper objectMapper;

    public Appointment appointmentBuilder(AppointmentRequest appointmentRequest) {

        UserDTO userDTO = userService.findById(appointmentRequest.getUserId());
        User user = objectMapper.getUserEntityFromDTO(userDTO);
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentRequest.getAppointmentDate());
        appointment.setUser(user);
        Location location = locationService.findById(appointmentRequest.getLocationId());
        appointment.setLocation(location);
        return appointment;
    }
}
