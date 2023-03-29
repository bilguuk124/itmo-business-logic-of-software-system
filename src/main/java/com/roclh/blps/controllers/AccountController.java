package com.roclh.blps.controllers;

import com.roclh.blps.Exceptions.AccountNotFountException;
import com.roclh.blps.entities.Account;
import com.roclh.blps.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AccountController {

    private final Logger log = LogManager.getLogger(AccountController.class);
    private final AccountService accountService;

    @GetMapping("/accounts")
    public List<Account> getAllAccounts(){
        return  accountService.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerAccount(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(accountService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginAccount(
            @RequestBody LoginRequest request
    ) throws AccountNotFountException {
        return ResponseEntity.ok(accountService.login(request));
    }

    @PostMapping("/admin-register")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(accountService.registerAdmin(request));
    }
}
