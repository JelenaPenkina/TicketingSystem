package ee.sda.ticketingsystem.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserExistsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserExists {

    String message() default "User with this Id does not exist.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


















//    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");
//    public static boolean isValidPassword(String password) {
//        return PASSWORD_PATTERN.matcher(password).matches();
//    }
//    public static void main(String[] args) {
//        String password = "StrongP@sswOrd";
//
//        if (isValidPassword(password)) {
//            System.out.println("Password is valid");
//        }
//        System.out.println("Password is invalid");
//    }
}
