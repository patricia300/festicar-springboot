package com.bdi.projectbdigroup5.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import com.bdi.projectbdigroup5.model.SousDomaine;
import com.bdi.projectbdigroup5.repository.SousDomaineRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class SousDomaineService {
    private SousDomaineRepository sousDomaineRepository;

    public SousDomaine createSousDomaine(SousDomaine sousDomaine){
        return sousDomaineRepository.save(sousDomaine);
    }

    public Iterable<SousDomaine> createSousdomaines(Iterable<SousDomaine> sousDomaines) {
        return sousDomaineRepository.saveAll(sousDomaines);
    }

    public Iterable<SousDomaine> findAllSousDomaine() {
        return sousDomaineRepository.findAll();
    }

    public Optional<SousDomaine> findById(String nom){
        return sousDomaineRepository.findById(nom);
    }
}
