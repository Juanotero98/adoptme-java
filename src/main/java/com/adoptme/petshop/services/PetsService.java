package com.adoptme.petshop.services;

import com.adoptme.petshop.entities.Pet;
import com.adoptme.petshop.entities.User;
import com.adoptme.petshop.repositories.PetsRepository;
import com.adoptme.petshop.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetsService {

    @Autowired private UsersRepository usersRepository;
    @Autowired private PetsRepository petsRepository;

    public List<Pet> findAll() {
        return petsRepository.findAll();
    }

    public Optional<Pet> findById(Long id) {
        return petsRepository.findById(id);
    }

    public Pet save(Pet pet) {
        return petsRepository.save(pet);
    }

    public void deleteById(Long id) {
        petsRepository.deleteById(id);
    }

    public Pet adoptPet(Long petId, Long userId) throws Exception {
        Optional<Pet> pet = petsRepository.findById(petId);
        if (!pet.isPresent()) {
            throw new Exception("Pet not found with id: " + petId);
        }
        Optional<User> user = usersRepository.findById(userId);
        if (!user.isPresent()) {
            throw new Exception("User not found with id: " + petId);
        }
        Pet foundPet = pet.get();
        User foundUser = user.get();
        foundPet.setOwner(foundUser);
        petsRepository.save(foundPet);
        return foundPet;
    }

}