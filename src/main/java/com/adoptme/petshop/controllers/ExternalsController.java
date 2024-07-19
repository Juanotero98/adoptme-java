package com.adoptme.petshop.controllers;

import com.adoptme.petshop.entities.User;
import com.adoptme.petshop.services.ExternalUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation .*;

import java.util .*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("api/ext/users")
@Tag(name = "Rutas de usuarios externos", description = " CRUD de usuarios de la API de JSONplaceholder")
public class ExternalsController {

    @Autowired private ExternalUser service;

    @PostMapping
    public ResponseEntity<User> saveExt(@RequestBody User user) {
        try {
            return new ResponseEntity<>(service.saveExt(user), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllPersonsExt() {
        try {
            List<User> users = service.findAllPersonsExt();
            if (!users.isEmpty()) {
                return ResponseEntity.ok(users);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdExt(@PathVariable Long id) {
        try {
            User user = service.findByIdExt(id);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateByIdExt(@PathVariable Long id, @RequestBody User data) {
        try {
            User user = service.findByIdExt(id);
            if (user != null) {
                if (data.getName() != null) {
                    user.setName(data.getName());
                }
                if (data.getEmail() != null) {
                    user.setEmail(data.getEmail());
                }
                service.updateExt(id, user);
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteByIdExt(@PathVariable Long id) {
        try {
            User user = service.findByIdExt(id);
            if (user != null) {
                service.deleteByIdExt(id);
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
