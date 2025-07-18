package com.ra.ss8.controller;

import com.ra.ss8.model.request.AccountRequestDTO;
import com.ra.ss8.model.resonse.DataResponse;
import com.ra.ss8.model.entity.Account;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @PostMapping
    public ResponseEntity<DataResponse<Account>> postAccount(@Valid @RequestBody AccountRequestDTO accountRequestDTO) {
        return new ResponseEntity<>(new DataResponse<Account>(), HttpStatus.CREATED);
    }
}