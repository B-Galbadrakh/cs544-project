package cs544.group1.project.service.mappers;

import cs544.group1.project.domain.Location;
import cs544.group1.project.dto.LocationResponse;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationResponseMapper extends BaseMapper<Location, LocationResponse> {
    @Autowired
    public LocationResponseMapper(MapperFactory mapperFactory){
        super(mapperFactory, Location.class, LocationResponse.class);
    }

}
