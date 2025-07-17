package com.ra.ss7.controller;

import com.ra.ss7.model.dto.DataResponse;
import com.ra.ss7.model.entity.User;
import com.ra.ss7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController{
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<DataResponse<List<User>>> getAllUsers(){
        return ResponseEntity.ok(new DataResponse<>(userService.getAllUsers(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<User>> saveUser(@RequestBody User user){
        return ResponseEntity.ok(new DataResponse<>(userService.createUser(user), HttpStatus.CREATED));
    }

    @PutMapping
    public ResponseEntity<DataResponse<User>> updateUser(@RequestBody User user){
        return ResponseEntity.ok(new DataResponse<>(userService.updateUser(user.getUser_id(), user), HttpStatus.OK));
    }

    @GetMapping("{id}")
    public ResponseEntity<DataResponse<User>> getUserById(Long id){
        return ResponseEntity.ok(new DataResponse<>(userService.findById(id), HttpStatus.OK));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DataResponse<Void>> deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok(new DataResponse<>(null, HttpStatus.NO_CONTENT));
    }
}