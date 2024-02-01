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

    public Iterable<FestivalResponseDto> getAllFestivalByCommune(String communecodeInsee) {
        return festivalRepository.findAllByCommuneCodeInsee(communecodeInsee)
                .stream().map(f -> createFestivalResponseDtoFromFestival(f, List.of()))
                .toList();
    }

    public Iterable<FestivalResponseDto> getAllFestivalByDateDebut(String dateDebut) {

            return festivalRepository.findAllByDateDebut(new Date(dateDebut))
                    .stream().map(f -> createFestivalResponseDtoFromFestival(f, List.of()))
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

    public List<FestivalResponseDto> getAllFestivalsByName(String nom){
        return this.festivalRepository.findAllByNomContainingIgnoreCase(nom)
                .stream()
                .map(f -> createFestivalResponseDtoFromFestival(f, List.of()))
                .toList();
    }

    public List<FestivalResponseDto> getAllFestivalsByDomaine(String domaine){
        return this.festivalRepository.findAllBySousDomaineDomainePrincipalNomContainingIgnoreCase(domaine)
                .stream()
                .map(f -> createFestivalResponseDtoFromFestival(f, List.of()))
                .toList();
    }
}
