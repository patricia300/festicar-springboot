package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.PointPassageCovoiturage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointPassageCovoiturageRepository extends CrudRepository<PointPassageCovoiturage, Long> {
    List<PointPassageCovoiturage> findAllByOffreCovoiturageFestivalIdAndLieuCovoiturageCommuneCodeInseeOrLieuCovoiturageCommuneDepartementNumeroOrLieuCovoiturageCommuneDepartementRegionNom(Long offreCovoiturage_festival_id, String lieuCovoiturage_commune_codeInsee, String lieuCovoiturage_commune_departement_numero, String lieuCovoiturage_commune_departement_region_nom);
}
