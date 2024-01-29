package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.model.PointPassageCovoiturage;
import com.bdi.projectbdigroup5.repository.PointPassageCovoiturageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PointPassageCovoiturageService {
    
    @Autowired
    private PointPassageCovoiturageRepository pointPassageCovoiturageRepository;

    public PointPassageCovoiturage pointPassageCovoiturageAleatoire(int nombrePlaces){
        Long count = pointPassageCovoiturageRepository.count();
        int nombrePageAleatoire = (int)(Math.random() * count);
        Pageable pageAleatoire = PageRequest.of(nombrePageAleatoire, 1);
        Page<PointPassageCovoiturage> pointPassageCovoiturage = pointPassageCovoiturageRepository.findAll(pageAleatoire );

        return pointPassageCovoiturage.getContent().get(0);
    }

    public Iterable<PointPassageCovoiturage> createPointPassageCovoiturages(Iterable<PointPassageCovoiturage> pointPassageCovoiturages){
        return pointPassageCovoiturageRepository.saveAll(pointPassageCovoiturages);
    }

}
