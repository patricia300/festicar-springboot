package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.User;
import com.bdi.projectbdigroup5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAllUsers(){
       return userRepository.findAll();
    }

}
