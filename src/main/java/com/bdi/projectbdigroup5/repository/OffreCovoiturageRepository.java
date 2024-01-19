package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.OffreCovoiturage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffreCovoiturageRepository extends CrudRepository<OffreCovoiturage, Long> {
}
