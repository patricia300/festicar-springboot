package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.Covoitureur;
import com.bdi.projectbdigroup5.repository.CovoitureurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CovoitureurService {
    @Autowired
    private CovoitureurRepository covoitureurRepository;

    public Covoitureur createCovoitureur(Covoitureur covoitureur){
        return covoitureurRepository.save(covoitureur);
    }

    public Iterable<Covoitureur> createCovoitureurs(Iterable<Covoitureur> covoitureurs){
        return covoitureurRepository.saveAll(covoitureurs);
    }
}
