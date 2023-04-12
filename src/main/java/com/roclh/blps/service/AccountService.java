package com.roclh.blps.service;

import com.roclh.blps.Exceptions.AccountNotFountException;
import com.roclh.blps.security.JwtService;
import com.roclh.blps.RequestAndResponse.AuthenticationResponse;
import com.roclh.blps.RequestAndResponse.LoginRequest;
import com.roclh.blps.RequestAndResponse.RegisterRequest;
import com.roclh.blps.database.AccountDatabase;
import com.roclh.blps.entities.Account;
import com.roclh.blps.entities.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountDatabase database;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public List<Account> findAll(){
        return database.findAll();
    }

    public AuthenticationResponse register(RegisterRequest request){
        Account user = Account.builder()
                .firstName(request.getFistName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        database.add(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse registerAdmin(RegisterRequest request){
        Account admin = Account.builder()
                .firstName(request.getFistName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        database.add(admin);
        var jwtToken = jwtService.generateToken(admin);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse login(LoginRequest request) throws AccountNotFountException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        Optional<Account> user = database.findByUsername(request.getUsername());
        if (user.isEmpty()) throw new AccountNotFountException();
        Account account = user.get();
        var jwtToken = jwtService.generateToken(account);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
