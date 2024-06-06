package com.example.crm_for_learning_center.controller;

import com.example.crm_for_learning_center.entity.User;
import com.example.crm_for_learning_center.payload.ApiResponse;
import com.example.crm_for_learning_center.payload.LoginDto;
import com.example.crm_for_learning_center.payload.RegisterDto;
import com.example.crm_for_learning_center.repository.UserRepository;
import com.example.crm_for_learning_center.security.JwtProvider;
import com.example.crm_for_learning_center.service.AuthService;
import com.example.crm_for_learning_center.service.SideBarMenuService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    SideBarMenuService sideBarMenuService;


    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody RegisterDto registerDto){
       ApiResponse apiResponse = authService.registerUser(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PostMapping("/verifyEmail")
    public  HttpEntity<?> verifyEmail(@RequestParam String emailCode, @RequestParam String email){
        ApiResponse apiResponse = authService.verifyEmail(email, emailCode);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?>  login(@RequestBody LoginDto loginDto){
        try{
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
            System.out.println(loginDto);
            System.out.println(authenticate);
            User user = (User) authenticate.getPrincipal();
            System.out.println(user.getEmail());
            String token = jwtProvider.generateToken(user.getEmail());
            return ResponseEntity.ok(token);
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse("Password or email is incorrect",false));
        }
    }

    @GetMapping("/getSideBarMenu")
    public HttpEntity<?> getSideBarMenu(@RequestParam UUID id){
       return ResponseEntity.ok(sideBarMenuService.getSideBarMenuBasedUser(id));
    }
}
