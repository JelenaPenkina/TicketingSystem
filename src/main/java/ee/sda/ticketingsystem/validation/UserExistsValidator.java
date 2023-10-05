package ee.sda.ticketingsystem.validation;

import ee.sda.ticketingsystem.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UserExistsValidator implements ConstraintValidator<UserExists, Integer> {

    @Autowired
    UserRepository userRepository;

    @Override
    public void initialize(UserExists constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer userId, ConstraintValidatorContext context) {
        return userRepository.existsById(userId);
    }
}
