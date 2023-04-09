package com.example.FlexStaff.Auth;

import com.example.FlexStaff.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {

        return ResponseEntity.ok(service.authenticate(request));
    }





    @PostMapping("/register/partner")
    public ResponseEntity<Object> registerP(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerB(request));
    }
    @PostMapping("/authenticate/partner")
    public ResponseEntity<AuthenticationResponse> authenticateP(
            @RequestBody AuthenticationRequest request
    ) {

        return ResponseEntity.ok(service.authenticateB(request));
    }



}
