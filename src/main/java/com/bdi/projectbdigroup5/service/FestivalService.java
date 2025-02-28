package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.dto.FestivalResponseDto;
import com.bdi.projectbdigroup5.exception.NotFoundException;
import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.repository.FestivalRepository;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


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
                .map(f -> {
                    int nbPlaceOffreCovoiturage = f.getOffreCovoiturages()
                            .stream()
                            .reduce(0,(totalPlaces, element ) -> totalPlaces + element.getNombrePlaces(), Integer::sum);
                    return createFestivalResponseDtoFromFestival(f, List.of(), nbPlaceOffreCovoiturage);
                })
                .toList();
    }

    public Iterable<FestivalResponseDto> getAllFestivalPerPage(Pageable pageable) {
        return festivalRepository.findAll(pageable).map(festival -> {
            int nbPlaceOffreCovoiturage = festival.getOffreCovoiturages()
                    .stream()
                    .reduce(0,(totalPlaces, element ) -> totalPlaces + element.getNombrePlaces(), Integer::sum);
            return createFestivalResponseDtoFromFestival(festival, List.of(),nbPlaceOffreCovoiturage);
        });
    }

    public List<FestivalResponseDto> getAllFestivalByCommune(String communecodeInsee) {
        return festivalRepository.findAllByCommuneCodeInsee(communecodeInsee)
                .stream().map(f -> {
                    int nbPlaceOffreCovoiturage = f.getOffreCovoiturages()
                            .stream()
                            .reduce(0,(totalPlaces, element ) -> totalPlaces + element.getNombrePlaces(), Integer::sum);
                    return createFestivalResponseDtoFromFestival(f, List.of(),nbPlaceOffreCovoiturage);
                })
                .toList();
    }

    public List<FestivalResponseDto> getAllFestivalByDateDebut(String dateDebut) {

            return festivalRepository.findAllByDateDebut(new Date(dateDebut))
                    .stream().map(f -> {
                        int nbPlaceOffreCovoiturage = f.getOffreCovoiturages()
                                .stream()
                                .reduce(0,(totalPlaces, element ) -> totalPlaces + element.getNombrePlaces(), Integer::sum);
                        return createFestivalResponseDtoFromFestival(f, List.of(),nbPlaceOffreCovoiturage);
                    })
                    .toList();
    }

    public FestivalResponseDto getOneFestival(Long id){
        Festival festival = this.festivalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Festival not found"));
        int nbPlaceOffreCovoiturage = festival.getOffreCovoiturages()
                .stream()
                .reduce(0,(totalPlaces, element ) -> totalPlaces + element.getNombrePlaces(), Integer::sum);

        return  createFestivalResponseDtoFromFestival(
                festival,
                createOffreCovoiturageFestivalDtos(festival.getOffreCovoiturages()),
                nbPlaceOffreCovoiturage
        );
    }

    public List<FestivalResponseDto> getAllFestivalsByName(String nom){
        return this.festivalRepository.findAllByNomContainingIgnoreCase(nom)
                .stream()
                .map(f -> {
                    int nbPlaceOffreCovoiturage = f.getOffreCovoiturages()
                            .stream()
                            .reduce(0,(totalPlaces, element ) -> totalPlaces + element.getNombrePlaces(), Integer::sum);
                    return createFestivalResponseDtoFromFestival(f, List.of(),nbPlaceOffreCovoiturage);
                })
                .toList();
    }

    public List<FestivalResponseDto> getAllFestivalsByDomaine(String domaine){
        return this.festivalRepository.findAllBySousDomaineDomainePrincipalNomContainingIgnoreCaseOrSousDomaineNomContainingIgnoreCase(domaine, domaine)
                .stream()
                .map(f -> {
                    int nbPlaceOffreCovoiturage = f.getOffreCovoiturages()
                            .stream()
                            .reduce(0,(totalPlaces, element ) -> totalPlaces + element.getNombrePlaces(), Integer::sum);
                    return createFestivalResponseDtoFromFestival(f, List.of(),nbPlaceOffreCovoiturage);
                })
                .toList();
    }

    public Festival findById(Long id){
        return this.festivalRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Festival not found"));
    }
}
