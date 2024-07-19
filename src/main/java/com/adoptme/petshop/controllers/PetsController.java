package com.adoptme.petshop.controllers;

import com.adoptme.petshop.entities.Pet;
import com.adoptme.petshop.entities.User;
import com.adoptme.petshop.services.PetsService;
import com.adoptme.petshop.services.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("api/V1/pets")
@Tag(name = "Rutas de mascotas", description = " CRUD de mascotas")
public class PetsController {

    @Autowired private PetsService service;
    @Autowired private UsersService usersService;

    @PostMapping
    @Operation (summary = "Crear una mascota", description = "Necesita que le pase un objeto con los datos de la mascota y devuelve la mascota creada")
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        try {
            return new ResponseEntity<>(service.save(pet), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    @Operation (summary = "leer todas las mascotas", description = "Devuelve todas las mascotas de la base de datos. Seria interesante realizar consulta y filtros")
    public ResponseEntity<List<Pet>> getAllPets() {
        try {
            List<Pet> pets = service.findAll();
            if (!pets.isEmpty()) {
                return ResponseEntity.ok(pets);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    @Operation (summary = "Lee la mascota con id indicado", description = "Devuelve la mascota que tiene ese id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve correctamente la mascota encontrada"),
            @ApiResponse(responseCode = "404", description = "No devuelve nada!"),
            @ApiResponse(responseCode = "500", description = "Servidor caido por probelmas diversos")
    })
    public ResponseEntity<Object> getPetById(@PathVariable Long id) {
        try {
            Optional<Pet> pet = service.findById(id);
            if (pet.isPresent()) {
                return ResponseEntity.ok(pet.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody Pet data) {
        try {
            Optional<Pet> optionalPet = service.findById(id);
            if (optionalPet.isPresent()) {
                Pet pet = optionalPet.get();
                pet.setName(data.getName());
                pet.setAge(data.getAge());
                pet.setOwner(pet.getOwner());
                return ResponseEntity.ok(service.save(pet));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        try {
            Optional<Pet> optionalPet = service.findById(id);
            if (optionalPet.isPresent()) {
                service.deleteById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{petId}/adopt/{personId}")
    public ResponseEntity<Pet> adoptPet(@PathVariable Long petId, @PathVariable Long personId) {
        try {
            service.adoptPet(petId, personId);
            return ResponseEntity.ok().build();
        } catch  (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
