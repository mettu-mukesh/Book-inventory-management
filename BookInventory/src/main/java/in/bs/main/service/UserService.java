package in.bs.main.service;

import in.bs.main.dto.UserRegistrationDTO;
import in.bs.main.entities.User;
import in.bs.main.repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> oppacc = userRepo.findByUsername(username);

        if (!oppacc.isPresent()) {
            throw new RuntimeException("The username is not present");
        }

        User user = oppacc.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    // User registration method
    public User registerUser(UserRegistrationDTO userRegistrationDTO) {
        // Check if username already exists
        Optional<User> existingUser = userRepo.findByUsername(userRegistrationDTO.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // Create new User entity
        User newUser = new User();
        newUser.setUsername(userRegistrationDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword())); // Encode the password
        newUser.setRole(userRegistrationDTO.getRole()); // Set the user role
        newUser.setEmail(userRegistrationDTO.getEmail());

        // Save the new user
        return userRepo.save(newUser);
    }


    //get method
    public User findByUsername(String username) {
        Optional<User> userOpt = userRepo.findByUsername(username);
        return userOpt.orElse(null);
    }


    public User findByEmail(String email) {
        Optional<User> userOpt = userRepo.findByEmail(email); // Use findByEmail instead of findByUsername
        return userOpt.orElse(null); // Return user or null if not found
    }
    
    //delete
    @Transactional // Ensures this method runs within a transaction
    public void deleteByUsername(String username) {
            userRepo.deleteByUsername(username);
    }



}
