package com.app.TODO_backend.controller;

import com.app.TODO_backend.dto.ChangeUserPasswordDto;
import com.app.TODO_backend.dto.JwtRequest;
import com.app.TODO_backend.dto.JwtResponse;
import com.app.TODO_backend.dto.RegistrationUserDto;
import com.app.TODO_backend.entity.User;
import com.app.TODO_backend.exceptions.ApplicationError;
import com.app.TODO_backend.repository.ChangePasswordTokenRepository;
import com.app.TODO_backend.service.EmailService;
import com.app.TODO_backend.service.UserService;
import com.app.TODO_backend.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Auth")
public class AuthController {
    private final UserService userService;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final ChangePasswordTokenRepository changePasswordTokenRepository;
    private final EmailService emailService;

//    tt7545946@gmail.com

    @Operation(description = "Получение токена по логину и паролю")
    @PostMapping("/authentication")
    @ApiResponse(responseCode = "200",description = "token",
            content = @Content(schema = @Schema(implementation = JwtResponse.class),mediaType = "application/json"))
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest request) {
        log.warn(request.toString());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException exception) {
            return new ResponseEntity<>(new ApplicationError(HttpStatus.UNAUTHORIZED.value(), "Invalid username or password"),
                    HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        String token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        if (userService.findByLogin(registrationUserDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(new ApplicationError(HttpStatus.UNAUTHORIZED.value(), "User already exists"), HttpStatus.UNAUTHORIZED);
        }
        if (userService.findByMail(registrationUserDto.getMail()).isPresent()) {
            return new ResponseEntity<>(new ApplicationError(HttpStatus.UNAUTHORIZED.value(), "User with  this email already exists"), HttpStatus.UNAUTHORIZED);
        }
        User user = new User();
        user.setLogin(registrationUserDto.getUsername());
        user.setMail(registrationUserDto.getMail());
        user.setPassword(registrationUserDto.getPassword());
        userService.createNewUser(user);
        return ResponseEntity.ok("User has been created");
    }

    @PostMapping("/confirm-reset")
    public ResponseEntity<?> confirmResetPassword(@RequestBody ChangeUserPasswordDto dto) {
        log.warn(dto);
        if (!changePasswordTokenRepository.verifyToken(dto.getToken())) {
            return new ResponseEntity<>(new ApplicationError(HttpStatus.NOT_FOUND.value(), "Invalid token"), HttpStatus.NOT_FOUND);
        }
        String mail = SecurityContextHolder.getContext().getAuthentication().getName();
        //Нужен ли запрос?
        Optional<User> user = userService.findByMail(mail);
        if (user.isEmpty()) {
            return new ResponseEntity<>(new ApplicationError(HttpStatus.NOT_FOUND.value(), "User does not exist"), HttpStatus.NOT_FOUND);
        }
        userService.changeUserPassword(user.get(), encoder.encode(dto.getPassword()));
        return ResponseEntity.ok("Password has been changed");
    }

    @GetMapping ("/reset-password")
    public ResponseEntity<?> resetPassword() {
        String mail = SecurityContextHolder.getContext().getAuthentication().getName();
        log.warn(mail);
        //Необходим ли запрос?
        Optional<User> user = userService.findByMail(mail);
        if (user.isEmpty()) {
            return new ResponseEntity<>(new ApplicationError(HttpStatus.UNAUTHORIZED.value(), "User does not exist"), HttpStatus.UNAUTHORIZED);
        }
        String token = changePasswordTokenRepository.generateToken();
        emailService.sendMailMessage(mail, "Password changing", String.format(
                "Hi %s, code: %s", user.get().getLogin(), token)
        );
        return ResponseEntity.ok("");
    }
}
