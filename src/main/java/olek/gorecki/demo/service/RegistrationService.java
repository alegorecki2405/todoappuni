package olek.gorecki.demo.service;


import lombok.AllArgsConstructor;
import olek.gorecki.demo.entities.Gender;
import olek.gorecki.demo.entities.Role;
import olek.gorecki.demo.entities.User;
import olek.gorecki.demo.registration.RegistrationRequest;
import olek.gorecki.demo.registration.token.ConfirmationToken;
import olek.gorecki.demo.registration.token.ConfirmationTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest request) {

        String token = userService.signUpUser(
                new User(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        Role.valueOf(request.getRole()),
                        Gender.valueOf(request.getGender())
                )
        );

        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;

        return link;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }

}
