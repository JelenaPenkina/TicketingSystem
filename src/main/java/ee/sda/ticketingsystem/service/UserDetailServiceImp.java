package ee.sda.ticketingsystem.service;

import ee.sda.ticketingsystem.component.CustomUserDetails;
import ee.sda.ticketingsystem.entity.User;
import ee.sda.ticketingsystem.enums.UserType;
import ee.sda.ticketingsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class UserDetailServiceImp implements UserDetailsService {

    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImp.class);

    public User findByUsername(String username) {
        User user = userRepository.findByEmail(username);
        System.out.println("Retrieved user: " + user.getEmail());
        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        System.out.println("Attempting to load user with email: " + username);
        logger.info("Attempting to load user with email: {}", username);
        User user = userRepository.findByEmail(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (user.getUserType()== UserType.AGENT) {
            authorities.add(new SimpleGrantedAuthority("ROLE_AGENT"));
        }else {
            authorities.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        }

        logger.info("Loaded user: {} with role: {}", user.getEmail(), user.getUserType());


        return new CustomUserDetails(user, authorities);
    }
}
