package com.g3.elis.util;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.g3.elis.model.User;
import com.g3.elis.repository.UserRepository;

@Service
public class PasswordResetService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OtpService otpService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean resetPassword(String email, String otp, String newPassword) {
        if (otpService.validateOtp(email, otp)) {
            User user = userRepository.findByEmail(email);
            if (user != null) {
                user.setPassword(passwordEncoder.encode(newPassword)); // Hash the password
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }
    

    public void updatePassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
