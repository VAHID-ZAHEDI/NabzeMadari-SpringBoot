package com.example.api.pregnancy.controllers;


import com.example.api.pregnancy.models.*;
import com.example.api.pregnancy.payload.request.SignupRequest;
import com.example.api.pregnancy.payload.response.JwtResponse;
import com.example.api.pregnancy.payload.response.MessageResponse;
import com.example.api.pregnancy.repositories.RoleRepository;
import com.example.api.pregnancy.repositories.UserRepository;
import com.example.api.pregnancy.security.jwt.JwtUtils;
import com.example.api.pregnancy.security.services.OtpService;
import com.example.api.pregnancy.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    public final static String SESSION_KEY_SMS_CODE = "SESSION_KEY_SMS_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    public OtpService otpService;


    @PostMapping("/generateOtp")
    public @ResponseBody
    ResponseEntity<?> generateOtp(String phoneNumber) {
        int otp = otpService.generateOTP(phoneNumber);
        SmsCode smsCode = new SmsCode(phoneNumber, String.valueOf(otp), 10);
        System.out.println(phoneNumber);
        System.out.println("code is  :" + smsCode.getCode());
        System.out.println("phone is  :" + phoneNumber);
//Generate The Template to send OTP
        return ResponseEntity.ok(smsCode);
    }

    @PostMapping(value = "/validateOtp")
    public @ResponseBody
    ResponseEntity<?> validateOtp(String phoneNumber, int otp) {
        if (otp >= 0) {
            int serverOtp = otpService.getOtp(phoneNumber);
            System.out.println("serverOTP : " + serverOtp);
            System.out.println("user otp : " + otp);
            if (serverOtp > 0) {
                if (otp == serverOtp) {
                    otpService.clearOTP(phoneNumber);
                    System.out.println("user exists :" + userRepository.existsByPhoneNumber(phoneNumber));
                    if (userRepository.existsByPhoneNumber(phoneNumber)) {
                        Optional<User> oUser = userRepository.findByPhoneNumber(phoneNumber);
                        oUser.get().setRegister(true);
//                        return ResponseEntity.ok((JwtResponse) authenticateUser(phoneNumber).getBody());
                        return ResponseEntity.ok(oUser.get());
                    } else {
                        return ResponseEntity.ok(new User());
                    }
                } else {
                    return ResponseEntity.badRequest().body(new ErrorModel("کد ارسالی صحیح نمی باشد.", 450));
                }
            } else {
                return ResponseEntity.badRequest().body(new ErrorModel("کد ارسالی منقضی شده است لطفا مجدد کد را دریافت کنید!", 451));
            }
        } else {
            return ResponseEntity.badRequest().body(new ErrorModel("کد ارسالی صحیح نمی باشد.", 450));
        }
    }


//************************************************************************


    //////////////////////////////////////
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @NotBlank String phoneNumber) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(phoneNumber, "123456789"));
//        AuthorityUtils.createAuthorityList("ROLE_USER");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUser().get_id(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        System.out.println("phhhh : " + signUpRequest.getPhoneNumber());

        if (userRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Create new user's account without SMS verify code
        User user = new User(signUpRequest.getPhoneNumber(), encoder.encode("123456789"), signUpRequest.getFirstName()
                , signUpRequest.getLastName(), true, signUpRequest.getAge());

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        Optional<User> user1 = userRepository.findByPhoneNumber(signUpRequest.getPhoneNumber());
        return ResponseEntity.ok(user1.get());
    }
}
