package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.Covoitureur;
import com.bdi.projectbdigroup5.model.Festivalier;
import com.bdi.projectbdigroup5.repository.CovoitureurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Covoitureur covoitureurAleatoir(){
        Long count = covoitureurRepository.count();
        int nombrePageAleatoire = (int)(Math.random() * count);
        Pageable pageAleatoire = PageRequest.of(nombrePageAleatoire, 1);
        Page<Covoitureur> covoitureurPage = covoitureurRepository.findAll(pageAleatoire );

        return covoitureurPage.getContent().get(0);
    }
}
