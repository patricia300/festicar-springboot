package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.dto.FestivalResponseDto;
import com.bdi.projectbdigroup5.model.Commune;
import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.model.OffreCovoiturage;
import com.bdi.projectbdigroup5.model.PointPassageCovoiturage;
import com.bdi.projectbdigroup5.repository.CommuneRepository;
import com.bdi.projectbdigroup5.repository.FestivalRepository;
import com.bdi.projectbdigroup5.repository.OffreCovoiturageRepository;
import com.bdi.projectbdigroup5.repository.PointPassageCovoiturageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.bdi.projectbdigroup5.dto.FestivalResponseDto.createFestivalResponseDtoFromFestival;
import static com.bdi.projectbdigroup5.dto.FestivalResponseDto.createOffreCovoiturageFestivalDtos;


@Service
public class OffreCovoiturageService {
    @Autowired
    private OffreCovoiturageRepository offreCovoiturageRepository;

    @Autowired
    private CommuneRepository communeRepository;

    @Autowired
    private PointPassageCovoiturageRepository pointPassageCovoiturageRepository;

    @Autowired
    private FestivalRepository festivalRepository;

    public OffreCovoiturage createOffreCovoiturage(OffreCovoiturage offreCovoiturage){
        return offreCovoiturageRepository.save(offreCovoiturage);
    }

    public Iterable<OffreCovoiturage> getAllOffreCovoiturages(Pageable pageable){
        return offreCovoiturageRepository.findAll(pageable);
    }

    public FestivalResponseDto getOffreCovoituragePlusProche(String communeCodeInsee, Long idFestival){
        Optional<Commune> commune = communeRepository.findById(communeCodeInsee);
        Optional<Festival> festival = festivalRepository.findById(idFestival);

        List<PointPassageCovoiturage> pointPassageCovoiturages = this.pointPassageCovoiturageRepository.findAllByOffreCovoiturageFestivalIdAndLieuCovoiturageCommuneCodeInseeOrLieuCovoiturageCommuneDepartementNumeroOrLieuCovoiturageCommuneDepartementRegionNom(
                idFestival,
                commune.get().getCodeInsee(),
                commune.get().getDepartement().getNumero(),
                commune.get().getDepartement().getRegion().getNom()
        );

        List<OffreCovoiturage> offreCovoiturages = pointPassageCovoiturages
                .stream()
                .map(o -> {
                    OffreCovoiturage offreCovoiturage = o.getOffreCovoiturage();
                    offreCovoiturage.setPointPassageCovoiturages(pointPassageCovoiturages);
                    return  offreCovoiturage;
                })
                .toList();

        int nbPlaceOffreCovoiturage = festival.get().getOffreCovoiturages()
                .stream()
                .reduce(0,(totalPlaces, element ) -> totalPlaces + element.getNombrePlaces(), Integer::sum);

        return  createFestivalResponseDtoFromFestival(
                festival.get(),
                createOffreCovoiturageFestivalDtos(offreCovoiturages),
                nbPlaceOffreCovoiturage
        );

    }
}
