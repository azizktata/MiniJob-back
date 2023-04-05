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
    @GetMapping("/token")
    public String getusername(){
       return jwtService.extractUsername("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJndHRAZ21haWwuY29tIn0.lloUqGOlAmiokU4kDoDEQif8TnxNA2OWims-Oh3Utcw");
    }


}
