package ee.sda.ticketingsystem.hydrator;

import ee.sda.ticketingsystem.dto.UserDTO;
import ee.sda.ticketingsystem.entity.User;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

@Component
@Accessors(chain = true)
public class UserHydrator {

    public UserDTO convertToDTO(User user) {
        return new UserDTO()
                .setId(user.getId())
                .setName(user.getName())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setUserType(user.getUserType())
                .setRegisteredAt(user.getRegisteredAt());
    }

    public User convertToEntity(UserDTO dto) {
        return new User()
                .setId(dto.getId())
                .setName(dto.getName())
                .setEmail(dto.getEmail())
                .setPassword(dto.getPassword())
                .setUserType(dto.getUserType())
                .setRegisteredAt(dto.getRegisteredAt());
    }
}


