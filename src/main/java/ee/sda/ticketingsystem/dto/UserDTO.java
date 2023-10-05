package ee.sda.ticketingsystem.dto;


import ee.sda.ticketingsystem.enums.UserType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    private Integer userId;

    @Size(max = 30, message = "Name must not exceed 30 characters")
    private String name;

    @Size(max = 40, message = "Name must not exceed 40 characters")
    private String email;

    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    private String password;

    @NotNull(message = "User type cannot be null")
    private UserType userType;

}
