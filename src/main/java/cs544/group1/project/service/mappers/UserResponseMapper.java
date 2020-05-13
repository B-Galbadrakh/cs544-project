package cs544.group1.project.service.mappers;

import cs544.group1.project.domain.User;
import cs544.group1.project.dto.UserDTO;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper extends BaseMapper<User, UserDTO>{

    @Autowired
    public UserResponseMapper(MapperFactory mapperFactory){
        super(mapperFactory, User.class, UserDTO.class);
    }
}
