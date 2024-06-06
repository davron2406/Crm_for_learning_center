package com.example.crm_for_learning_center.service;

import com.example.crm_for_learning_center.entity.User;
import com.example.crm_for_learning_center.payload.ApiResponse;
import com.example.crm_for_learning_center.payload.LoginDto;
import com.example.crm_for_learning_center.payload.RegisterDto;
import com.example.crm_for_learning_center.repository.RoleRepository;
import com.example.crm_for_learning_center.repository.UserRepository;
import com.example.crm_for_learning_center.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow();
    }



    public ApiResponse registerUser(RegisterDto registerDto) {
        if(userRepository.existsByEmail(registerDto.getEmail()))
            return new ApiResponse("Email already exist",false);
        User user = new User(
                registerDto.getFullName(),passwordEncoder.encode(registerDto.getPassword()), registerDto.getEmail(),
                roleRepository.findByName(AppConstants.USER).get(),
                UUID.randomUUID().toString(),
                1
        );

        userRepository.save(user);
        sendEmail(user.getEmail(), user.getEmailCode());
        return new ApiResponse("User successfully registered", true);

    }


    public Boolean sendEmail(String sendingEmail, String emailCode){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("davronsaydullayev2406@gmail.com");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Validate your Email");
            mailMessage.setText("<a href='http://localhost:8080/api/auth/verifyEmail?emailCode=" + emailCode + "&email=" + sendingEmail + ">Tasdiqlang</a>");
            javaMailSender.send(mailMessage);
            return true;

        }catch (Exception e){
            return false;
        }
    }

    public ApiResponse verifyEmail(String email, String emailCode) {
       if(userRepository.existsByEmailAndEmailCode(email,emailCode)){
           User user = userRepository.findByEmail(email).get();
           user.setEmailCode(null);
           user.setEnabled(true);
           userRepository.save(user);
           return new ApiResponse("email successfully verified",true);
       }

       return new ApiResponse("User not Found",false);
    }


}
