package cs544.group1.project.util;

import cs544.group1.project.domain.User;
import cs544.group1.project.domain.UserRole;
import cs544.group1.project.domain.UserRoles;
import cs544.group1.project.dto.UserDTO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomObjectMapper {

    public User getUserEntityFromDTO(UserDTO dto) {
        try
        {
            User result = new User();
            BeanUtils.copyProperties(result,dto);
            List<UserRole> roles = Arrays.stream(dto.getRoles()).map(role -> {
                UserRole rle = new UserRole();
                rle.setUserRoles(role);
                return rle;
            }).collect(Collectors.toList());
            result.setRole(roles);
            return result;
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
            return null;
        }
    }

    public UserDTO getUserDTOFromEntity(User usr){
        try
        {
            UserDTO result = new UserDTO();
            BeanUtils.copyProperties(result,usr);
            if(usr.getRole() != null && !usr.getRole().isEmpty())
            {
                UserRoles[] roles = usr.getRole().stream().map(role -> role.getUserRoles()).collect(Collectors.toList()).toArray(new UserRoles[usr.getRole().size()]);
                result.setRoles(roles);
            }
            return result;
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
            return null;
        }
    }
}
