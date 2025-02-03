package com.nashspadok.backendserver.controller;

import com.nashspadok.backendserver.dto.UserLoginRequestDto;
import com.nashspadok.backendserver.dto.UserResponseDto;
import com.nashspadok.backendserver.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
