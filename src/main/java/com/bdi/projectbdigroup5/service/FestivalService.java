package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.dto.FestivalResponseDto;
import com.bdi.projectbdigroup5.dto.OffreCovoiturageFestivalDto;
import com.bdi.projectbdigroup5.exception.NotFoundException;
import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.repository.FestivalRepository;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
            Pageable pageable ) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateDebut);
        return this.festivalRepository
                .findAllByDateDebutAfterAndCommuneCodeInseeAndSousDomaineNomContainingOrSousDomaineDomainePrincipalNomContaining(
                date,
                communeCodeInsee,
                sousDomaine,
                domainePrincipal,
                pageable
        ).map(f -> FestivalResponseDto.builder()
                .id(f.getId())
                .nom(f.getNom())
                .nombrePass(f.getNombrePass())
                .siteWeb(f.getSiteWeb())
                .nomCommune(f.getCommune().getNom())
                .tarifPass(f.getTarifPass())
                .nomOrganisateur(f.getOrganisateur().getNom() + " " + f.getOrganisateur().getPrenom())
                .nomSousDomaine(f.getSousDomaine().getNom())
                .nomDomainePrincipal(f.getSousDomaine().getDomainePrincipal().getNom())
                .dateDebut(f.getDateDebut())
                .dateFin(f.getDateFin())
                .build()
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
        return festivalRepository.findAll(pageable).map(f -> FestivalResponseDto.builder()
                    .id(f.getId())
                    .nom(f.getNom())
                    .nombrePass(f.getNombrePass())
                    .siteWeb(f.getSiteWeb())
                    .nomCommune(f.getCommune().getNom())
                    .tarifPass(f.getTarifPass())
                    .nomOrganisateur(f.getOrganisateur().getNom() + " " + f.getOrganisateur().getPrenom())
                    .nomSousDomaine(f.getSousDomaine().getNom())
                    .nomDomainePrincipal(f.getSousDomaine().getDomainePrincipal().getNom())
                    .dateDebut(f.getDateDebut())
                    .dateFin(f.getDateFin())
                    .build()
        );
    }

    public Iterable<Festival> getAllFestivalByCommune(String commune, Pageable pageable) {
        return festivalRepository.findAllByCommuneNom(commune, pageable);
    }

    public Iterable<Festival> getAllFestivalByDateDebutOrDateFin(String dateDebut, String dateFin, Pageable pageable) {
        if (dateFin == null && dateDebut != null) {
            return festivalRepository.findAllByDateDebut(dateDebut, pageable);
        }
        if (dateDebut == null && dateFin != null) {
            return festivalRepository.findAllByDateFin(dateFin, pageable);
        }
        return festivalRepository.findAllByDateDebutOrDateFin(dateDebut, dateFin, pageable);
    }

    public Iterable<Festival> createFestivals(Iterable<Festival> festivals) {
        return festivalRepository.saveAll(festivals);
    }

    public FestivalResponseDto getOneFestivalById(Long id)
    {
        Festival festival =  festivalRepository.findById(id).orElseThrow(() -> new NotFoundException("Festival not found"));
        List<OffreCovoiturageFestivalDto> offreCovoiturageFestivalDtos = festival.getOffreCovoiturages().stream()
                .map(o -> OffreCovoiturageFestivalDto.builder()
                    .id(o.getId())
                    .dateOffre(o.getDateOffre())
                    .heureArrive(o.getHeureArrive())
                    .heureDepart(o.getHeureDepart())
                    .pointPassageCovoiturages(o.getPointPassageCovoiturages())
                    .covoitureur(o.getCovoitureur())
                    .modeleVoiture(o.getModeleVoiture())
                    .nombrePlaces(o.getNombrePlaces())
                    .build()
                ).toList();

        return FestivalResponseDto.builder()
                .id(festival.getId())
                .nom(festival.getNom())
                .nombrePass(festival.getNombrePass())
                .siteWeb(festival.getSiteWeb())
                .nomCommune(festival.getCommune().getNom())
                .offreCovoiturages(offreCovoiturageFestivalDtos)
                .tarifPass(festival.getTarifPass())
                .nomOrganisateur(festival.getOrganisateur().getNomComplet())
                .nomSousDomaine(festival.getSousDomaine().getNom())
                .nomDomainePrincipal(festival.getSousDomaine().getDomainePrincipal().getNom())
                .dateDebut(festival.getDateDebut())
                .dateFin(festival.getDateFin())
                .build();
    }

    public Iterable<FestivalResponseDto> getAllFestivalsByName(String nom, Pageable pageable) {
        return this.festivalRepository.findAllByNomContaining(nom, pageable)
                .stream().map(festival -> FestivalResponseDto.builder()
                    .id(festival.getId())
                    .nom(festival.getNom())
                    .nombrePass(festival.getNombrePass())
                    .siteWeb(festival.getSiteWeb())
                    .nomCommune(festival.getCommune().getNom())
                    .tarifPass(festival.getTarifPass())
                    .nomOrganisateur(festival.getOrganisateur().getNomComplet())
                    .nomSousDomaine(festival.getSousDomaine().getNom())
                    .nomDomainePrincipal(festival.getSousDomaine().getDomainePrincipal().getNom())
                    .dateDebut(festival.getDateDebut())
                    .dateFin(festival.getDateFin())
                    .build())
                .toList();
    }
}
