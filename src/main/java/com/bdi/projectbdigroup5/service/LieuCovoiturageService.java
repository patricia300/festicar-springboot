package com.bdi.projectbdigroup5.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdi.projectbdigroup5.model.LieuCovoiturage;
import com.bdi.projectbdigroup5.repository.LieuCovoiturageRepository;

@Service
public class LieuCovoiturageService {

    @Autowired
    private LieuCovoiturageRepository lieuCovoiturageRepository;

    public LieuCovoiturage createLieuCovoiturage(LieuCovoiturage lieuCovoiturage) {
        return lieuCovoiturageRepository.save(lieuCovoiturage);
    }

    public Iterable<LieuCovoiturage> createLieuCovoiturages(Iterable<LieuCovoiturage> lieux) {
        return lieuCovoiturageRepository.saveAll(lieux);
    }

    public Optional<LieuCovoiturage> findById(Long id) {
        return lieuCovoiturageRepository.findById(id);
    }

    public Iterable<LieuCovoiturage> findByNom(String nom) {
        return lieuCovoiturageRepository.findByNom(nom);
    }
}
