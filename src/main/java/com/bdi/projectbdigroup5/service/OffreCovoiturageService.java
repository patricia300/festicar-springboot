package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.OffreCovoiturage;
import com.bdi.projectbdigroup5.repository.OffreCovoiturageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffreCovoiturageService {
    @Autowired
    private OffreCovoiturageRepository offreCovoiturageRepository;

    public OffreCovoiturage createOffreCovoiturage(OffreCovoiturage offreCovoiturage){
        return offreCovoiturageRepository.save(offreCovoiturage);
    }
}
