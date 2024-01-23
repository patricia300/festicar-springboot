package com.bdi.projectbdigroup5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdi.projectbdigroup5.model.Commune;
import com.bdi.projectbdigroup5.repository.CommuneRepository;

import java.util.Optional;

@Service
public class CommuneService {
    
    @Autowired
    private CommuneRepository communeRepository;

    public Commune createCommune(Commune commune){
        return communeRepository.save(commune);
    }

    public Iterable<Commune> createCommunes(Iterable<Commune> communes) {
        return communeRepository.saveAll(communes);
    }

    public void deleteCommune(Commune commune){
        communeRepository.delete(commune);
    }

    public Commune updateCommune(Commune commune){
        return communeRepository.save(commune);
    }

    public Optional<Commune> findCommuneById(String codeInsee){
        return communeRepository.findById(codeInsee);
    }

    public Iterable<Commune> findAllCommunes(){
        return communeRepository.findAll();
    }
}
