package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.dto.FestivalResponseDto;
import com.bdi.projectbdigroup5.exception.NotFoundException;
import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.repository.FestivalRepository;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.Date;
import java.util.List;

import static com.bdi.projectbdigroup5.dto.FestivalResponseDto.createFestivalResponseDtoFromFestival;
import static com.bdi.projectbdigroup5.dto.FestivalResponseDto.createOffreCovoiturageFestivalDtos;

@Service
@AllArgsConstructor
public class FestivalService {
    private FestivalRepository festivalRepository;

    public Festival createFestival(Festival festival) {
        return festivalRepository.save(festival);
    }

    public Iterable<Festival> getAllFestivalByFilter(
            String dateDebut,
            String communeCodeInsee,
            String sousDomaine,
            Pageable pageable )
    {
        Date date = new Date(dateDebut);
        return this.festivalRepository.findAllByDateDebutAndCommuneCodeInseeAndSousDomaineNom(
                date,
                communeCodeInsee,
                sousDomaine,
                pageable
        );
    }

    public Festival festivalAleatoire() {
        Long count = festivalRepository.count();
        int nombrePageAleatoire = (int) (Math.random() * count);
        Pageable pageAleatoire = PageRequest.of(nombrePageAleatoire, 1);
        Page<Festival> festivalPage = festivalRepository.findAll(pageAleatoire);

        return festivalPage.getContent().get(0);
    }

    public Iterable<FestivalResponseDto> getAllFestivalPerPage(Pageable pageable) {
        return festivalRepository.findAll(pageable).map(festival -> createFestivalResponseDtoFromFestival(festival, List.of()));
    }

    public Iterable<Festival> getAllFestivalByCommune(String commune, Pageable pageable) {
        return festivalRepository.findAllByCommuneNom(commune, pageable);
    }

    public Iterable<Festival> getAllFestivalByDateDebutOrDateFin(String dateDebut, String dateFin, Pageable pageable) {
        if (dateFin == null && dateDebut != null) {
            return festivalRepository.findAllByDateDebut(new Date(dateDebut), pageable);
        }
        if (dateDebut == null && dateFin != null) {
            return festivalRepository.findAllByDateFin(new Date(dateFin), pageable);
        }
        return festivalRepository.findAllByDateDebutOrDateFin(new Date(dateDebut), new Date(dateFin), pageable);
    }

    public Iterable<Festival> createFestivals(Iterable<Festival> festivals) {
        return festivalRepository.saveAll(festivals);
    }

    public FestivalResponseDto getOneFestival(Long id){
        Festival festival = this.festivalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Festival not found"));

        return  createFestivalResponseDtoFromFestival(festival,createOffreCovoiturageFestivalDtos(festival.getOffreCovoiturages()) );
    }
}
