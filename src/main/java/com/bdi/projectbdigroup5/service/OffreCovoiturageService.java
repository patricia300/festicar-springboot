package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.exception.NotFoundException;
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

    public OffreCovoiturage createOffreCovoiturage(OffreCovoiturage offreCovoiturage) {
        return offreCovoiturageRepository.save(offreCovoiturage);
    }

    public Iterable<OffreCovoiturage> getAllOffreCovoiturages(Pageable pageable) {
        return offreCovoiturageRepository.findAll(pageable);
    }

    public OffreCovoiturage findByID(Long id) {
        return offreCovoiturageRepository.findById(id).orElseThrow(() -> new NotFoundException(
                "Offre Covoiturage non trouvé"));
    }
}
