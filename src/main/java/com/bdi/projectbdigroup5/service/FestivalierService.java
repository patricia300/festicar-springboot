package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.Festivalier;
import com.bdi.projectbdigroup5.repository.FestivalierRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.Optional;

@Service
public class FestivalierService {
    @Autowired
    private FestivalierRepository festivalierRepository;

    public Festivalier createFestivalier(Festivalier festivalier){
        return festivalierRepository.save(festivalier);
    }

    public Festivalier updateFestivalier(Festivalier festivalier){
        return festivalierRepository.save(festivalier);
    }

    public Iterable<Festivalier> createFestivaliers(Iterable<Festivalier> festivaliers){
        return festivalierRepository.saveAll(festivaliers);
    }

    public Optional<Festivalier> findById(String email){
        return festivalierRepository.findById(email);
    }

    public Optional<Festivalier> findByToken(String token){
        return festivalierRepository.findByToken(token);
    }

    public Festivalier festivalierAleatoire(){
        Long count = festivalierRepository.count();
        int nombrePageAleatoire = (int)(Math.random() * count);
        Pageable pageAleatoire = PageRequest.of(nombrePageAleatoire, 1);
        Page<Festivalier> festivalierPage = festivalierRepository.findAll(pageAleatoire );

        return festivalierPage.getContent().get(0);
    }   


}
