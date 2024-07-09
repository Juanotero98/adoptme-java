package com.adoptme.petshop.services;

import com.adoptme.petshop.entities.Pet;
import com.adoptme.petshop.repositories.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetsService {
    @Autowired
    private PetsRepository repository;

    public Pet save(Pet pet){
        return repository.save(pet);
    }

    public List<Pet> readAll(){
        return repository.findAll();
    }

    public Optional<Pet> readOne(Long id){
        return repository.findById(id);
    }

    public void destroyOne(Long id){
        repository.deleteById(id);
    }



}