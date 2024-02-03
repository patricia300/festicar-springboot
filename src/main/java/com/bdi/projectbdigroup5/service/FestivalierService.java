package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.Festivalier;
import com.bdi.projectbdigroup5.repository.FestivalierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class FestivalierService {
    @Autowired
    private FestivalierRepository festivalierRepository;

    public Festivalier createFestivalier(Festivalier festivalier){
        return festivalierRepository.save(festivalier);
    }

    public Optional<Festivalier> findById(String email){
        return festivalierRepository.findById(email);
    }

    public Optional<Festivalier> findByToken(String token){
        return festivalierRepository.findByToken(token);
    }

}
