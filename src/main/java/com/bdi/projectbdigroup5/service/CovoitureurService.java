package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.Covoitureur;
import com.bdi.projectbdigroup5.repository.CovoitureurRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Covoitureur covoitureurAleatoir() {
        Long count = covoitureurRepository.count();
        int nombrePageAleatoire = (int) (Math.random() * count);
        Pageable pageAleatoire = PageRequest.of(nombrePageAleatoire, 1);
        Page<Covoitureur> covoitureurPage = covoitureurRepository.findAll(pageAleatoire);

        return covoitureurPage.getContent().get(0);
    }
}
