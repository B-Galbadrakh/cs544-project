package cs544.group1.project.service.mappers;

import cs544.group1.project.domain.Appointment;
import cs544.group1.project.dto.AppointmentResponse;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentResponseMapper extends BaseMapper<Appointment, AppointmentResponse>{

    @Autowired
    public AppointmentResponseMapper(MapperFactory mapperFactory){
        super(mapperFactory, Appointment.class, AppointmentResponse.class);
    }

}
