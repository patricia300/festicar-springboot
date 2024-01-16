package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.User;
import com.bdi.projectbdigroup5.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAllUsers(){
       return userRepository.findAll();
    }

}
