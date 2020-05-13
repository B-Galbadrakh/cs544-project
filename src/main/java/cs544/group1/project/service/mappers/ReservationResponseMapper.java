package cs544.group1.project.service.mappers;

import cs544.group1.project.domain.Reservation;
import cs544.group1.project.dto.ReservationResponse;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationResponseMapper extends BaseMapper<Reservation, ReservationResponse>{
    @Autowired
    public ReservationResponseMapper(MapperFactory mapperFactory){
        super(mapperFactory, Reservation.class, ReservationResponse.class);
    }
}
