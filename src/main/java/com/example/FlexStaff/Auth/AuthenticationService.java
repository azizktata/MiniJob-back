package com.example.FlexStaff.Auth;

import com.example.FlexStaff.DAO.ClientRepo;
import com.example.FlexStaff.DAO.PartnerRepo;
import com.example.FlexStaff.Entities.Client;
import com.example.FlexStaff.Entities.Enum.Role;
import com.example.FlexStaff.Entities.Partner;
import com.example.FlexStaff.Exceptions.UserAlreadyExistsException;
import com.example.FlexStaff.Service.ClientService;
import com.example.FlexStaff.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ClientService clientService;
    private final ClientRepo clientRepo;
    private final PartnerRepo partnerRepo;
    //private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public String register(RegisterRequest request) {

        Client C = new Client();
        C.setFirstName(request.getFirstName());
        C.setLastName(request.getLastName());
        C.setEmail(request.getEmail());
        C.setPassword(passwordEncoder.encode(request.getPassword()));
        C.setRole(Collections.singleton(Role.CUser));
        var usr = clientRepo.findByEmail(request.getEmail());
        if(!usr.isEmpty())
            throw new UserAlreadyExistsException("User already exists");
        Client savedUser = clientRepo.save(C);
        var jwtToken = jwtService.generateToken(C);


        return jwtToken;

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
       /* authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );*/

         UserDetails user = clientService.loadUserByUsername(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Password is incorrect");
        }
        var jwtToken = jwtService.generateToken(user);

        AuthenticationResponse AR = new AuthenticationResponse();
        AR.setToken(jwtToken);

        return AR;
    }

    public AuthenticationResponse registerB(RegisterRequest request) {

        Partner P = new Partner();
        P.setFirstName(request.getFirstName());
        P.setLastName(request.getLastName());
        P.setEmail(request.getEmail());
        P.setPassword(passwordEncoder.encode(request.getPassword()));
        P.setRole(Collections.singleton(Role.BUser));

        var usr = partnerRepo.findByEmail(request.getEmail());
        if(!usr.isEmpty())
            throw new UserAlreadyExistsException("User already exists");
        var savedUser = partnerRepo.save(P);

        var jwtToken = jwtService.generateToken(P);
        AuthenticationResponse AR = new AuthenticationResponse(jwtToken);

        return AR;

    }

    public AuthenticationResponse authenticateB(AuthenticationRequest request) {

        var user = partnerRepo.findByEmail(request.getEmail())
                .orElseThrow();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Password is incorrect");
        }
        var jwtToken = jwtService.generateToken(user);

        AuthenticationResponse AR = new AuthenticationResponse(jwtToken);

        return AR;
    }
}
