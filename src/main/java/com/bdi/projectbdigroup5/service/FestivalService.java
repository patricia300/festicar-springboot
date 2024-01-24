package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.Covoitureur;
import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.repository.FestivalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class FestivalService {

    @Autowired
    private FestivalRepository festivalRepository;

    public Festival festivalAleatoire(){
        Long count = festivalRepository.count();
        int nombrePageAleatoire = (int)(Math.random() * count);
        Pageable pageAleatoire = PageRequest.of(nombrePageAleatoire, 1);
        Page<Festival> festivalPage = festivalRepository.findAll(pageAleatoire );

        return festivalPage.getContent().get(0);
    }
}
