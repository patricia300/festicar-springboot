package com.bdi.projectbdigroup5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdi.projectbdigroup5.model.SousDomaine;
import com.bdi.projectbdigroup5.repository.SousDomaineRepository;

import java.util.Optional;

@Service
public class SousDomaineService {
    
    @Autowired
    private SousDomaineRepository sousDomaineRepository;

    public SousDomaine createSousDomaine(SousDomaine sousDomaine){
        return sousDomaineRepository.save(sousDomaine);
    }

    public Optional<SousDomaine> findById(String nom){
        return sousDomaineRepository.findById(nom);
    }
}
