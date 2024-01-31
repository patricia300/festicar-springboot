package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.dto.FestivalResponseDto;
import com.bdi.projectbdigroup5.exception.NotFoundException;
import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.repository.FestivalRepository;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class FestivalService {
    private FestivalRepository festivalRepository;

    public Festival createFestival(Festival festival) {
        return festivalRepository.save(festival);
    }

    public Iterable<FestivalResponseDto> getAllFestivalByFilter(
            String dateDebut,
            String communeCodeInsee,
            String sousDomaine,
            String domainePrincipal,
            Pageable pageable )
    {
        Date date = new Date(dateDebut);
        return this.festivalRepository.findAllByDateDebutAfterAndCommuneCodeInseeAndSousDomaineNomContainingOrSousDomaineDomainePrincipalNomContaining(
                date,
                communeCodeInsee,
                sousDomaine,
                domainePrincipal,
                pageable)
                .map(f -> createFestivalResponseDtoFromFestival(f, List.of()))
                .toList();
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

    public Iterable<FestivalResponseDto> getAllFestivalByCommune(String commune, Pageable pageable) {
        return festivalRepository.findAllByCommuneNom(commune, pageable)
                .map(f -> createFestivalResponseDtoFromFestival(f, List.of()))
                .toList();
    }

    public Iterable<FestivalResponseDto> getAllFestivalByDateDebutOrDateFin(String dateDebut, String dateFin, Pageable pageable) {
        if (dateFin == null && dateDebut != null) {
            return festivalRepository.findAllByDateDebut(new Date(dateDebut), pageable)
                    .map(f -> createFestivalResponseDtoFromFestival(f, List.of()))
                    .toList();
        }
        if (dateDebut == null && dateFin != null) {
            return festivalRepository.findAllByDateFin(new Date(dateFin), pageable)
                    .map(f -> createFestivalResponseDtoFromFestival(f, List.of()))
                    .toList();
        }
        return festivalRepository.findAllByDateDebutOrDateFin(new Date(dateDebut), new Date(dateFin), pageable)
                .map(f -> createFestivalResponseDtoFromFestival(f, List.of()))
                .toList();
    }

    public Iterable<Festival> createFestivals(Iterable<Festival> festivals) {
        return festivalRepository.saveAll(festivals);
    }

    public FestivalResponseDto getOneFestival(Long id){
        Festival festival = this.festivalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Festival not found"));

        return  createFestivalResponseDtoFromFestival(festival, createOffreCovoiturageFestivalDtos(festival.getOffreCovoiturages()) );
    }

    public List<FestivalResponseDto> getAllFestivalsByName(String nom, Pageable pageable){
        return this.festivalRepository.findAllByNomContainingIgnoreCase(nom, pageable)
                .map(f -> createFestivalResponseDtoFromFestival(f, List.of()))
                .toList();
    }
}
