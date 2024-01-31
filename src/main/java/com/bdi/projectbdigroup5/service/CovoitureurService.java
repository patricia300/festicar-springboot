package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.Covoitureur;
import com.bdi.projectbdigroup5.repository.CovoitureurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CovoitureurService {
    private CovoitureurRepository covoitureurRepository;

    public Iterable<Covoitureur> findAllCovoitureur() {
        return covoitureurRepository.findAll();
    }

    public Covoitureur createCovoitureur(Covoitureur covoitureur) {
        return covoitureurRepository.save(covoitureur);
    }

    public Iterable<Covoitureur> createCovoitureurs(Iterable<Covoitureur> covoitureurs) {
        return covoitureurRepository.saveAll(covoitureurs);
    }

}
