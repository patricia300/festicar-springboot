package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.OffreCovoiturage;
import com.bdi.projectbdigroup5.repository.OffreCovoiturageRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class OffreCovoiturageService {
    private OffreCovoiturageRepository offreCovoiturageRepository;

    public OffreCovoiturage createOffreCovoiturage(OffreCovoiturage offreCovoiturage){
        return offreCovoiturageRepository.save(offreCovoiturage);
    }

    public Iterable<OffreCovoiturage> getAllOffreCovoiturages(Pageable pageable){
        return offreCovoiturageRepository.findAll(pageable);
    }
}
