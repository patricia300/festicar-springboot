package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.Covoitureur;
import com.bdi.projectbdigroup5.repository.CovoitureurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CovoitureurService {
    private CovoitureurRepository covoitureurRepository;

    public Covoitureur createCovoitureur(Covoitureur c) {
        return covoitureurRepository.save(c);
    }

    public Iterable<Covoitureur> findAllCovoitureur() {
        return covoitureurRepository.findAll();
    }
}
