package com.adoptme.petshop.controllers;



import com.adoptme.petshop.entities.User;
import com.adoptme.petshop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UsersController {
    @Autowired private UsersService service;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        try{
           return new ResponseEntity<>(service.save(user), HttpStatus.CREATED);
        }catch(Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> readAll(){
        try {
            List<User> users = service.readAll();
            return ResponseEntity.ok(users);
        }catch(Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
