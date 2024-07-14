package com.adoptme.petshop.services;

import com.adoptme.petshop.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExternalUser {

    private static RestTemplate restTemplate = new RestTemplate();
    private static String url = "https://jsonplaceholder.typicode.com/users";

    public User saveExt(User data) {
        return restTemplate.postForObject(url, data, User.class);
    }

    public List<User> findAllPersonsExt() {
        return restTemplate.getForObject(url, List.class);
    }

    public User findByIdExt(Long id) {
        return restTemplate.getForObject(url+"/"+id, User.class);
    }

    public void updateExt(Long id, User data) {
        restTemplate.put(url+"/"+id, data);
    }

    public void deleteByIdExt(Long id) {
        restTemplate.delete(url+"/"+id);
    }

}
