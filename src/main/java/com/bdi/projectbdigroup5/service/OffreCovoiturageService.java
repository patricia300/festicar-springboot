package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.OffreCovoiturage;
import com.bdi.projectbdigroup5.repository.OffreCovoiturageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class OffreCovoiturageService {
    private OffreCovoiturageRepository offreCovoiturageRepository;

    public Iterable<OffreCovoiturage> getAllOffreCovoiturages(Pageable pageable){
        return offreCovoiturageRepository.findAll(pageable);
    }
}
