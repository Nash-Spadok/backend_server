package com.nash_spadok.backend_server.controller;

import com.nash_spadok.backend_server.dto.UserLoginRequestDto;
import com.nash_spadok.backend_server.dto.UserResponseDto;
import com.nash_spadok.backend_server.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Validated
@Tag(name = "Authentication",
        description = "Operations related to user authentication such as registration, "
        + "login, and password management.")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "User Login", description = "Authenticate a user and return a JWT token.")
    @PostMapping("/login")
    public UserResponseDto login(@RequestBody @Valid UserLoginRequestDto request) {
        return authenticationService.login(request);
    }
}
