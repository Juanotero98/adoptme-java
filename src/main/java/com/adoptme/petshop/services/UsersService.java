package com.adoptme.petshop.services;


import com.adoptme.petshop.entities.User;
import com.adoptme.petshop.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired private UsersRepository repository;

    public User save(User user){
        return repository.save(user);
    }

    public List<User> readAll(){
        return repository.findAll();
    }

    public Optional<User> findById(Long id){
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }



}
