package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.repository.FestivalRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class FestivalService {
    private FestivalRepository festivalRepository;

    public Iterable<Festival> getAllFestivalPerPage(Pageable pageable) {
        return festivalRepository.findAll(pageable);
    }

    public Iterable<Festival> getAllFestivalByCommune(String commune,Pageable pageable){
        return festivalRepository.findAllByCommuneNom(commune, pageable);
    }

    public Iterable<Festival> getAllFestivalByDateDebutOrDateFin(String dateDebut, String dateFin, Pageable pageable )
    {
        if(dateFin == null && dateDebut != null)
        {
            return festivalRepository.findAllByDateDebut(dateDebut, pageable);
        }
        if(dateDebut == null && dateFin != null)
        {
            return  festivalRepository.findAllByDateFin(dateFin, pageable);
        }
        return festivalRepository.findAllByDateDebutOrDateFin(dateDebut, dateFin, pageable);
    }
}
