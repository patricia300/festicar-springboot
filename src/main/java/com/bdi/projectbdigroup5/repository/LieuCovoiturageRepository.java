package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.LieuCovoiturage;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface LieuCovoiturageRepository extends CrudRepository<LieuCovoiturage, Long>{
    Iterable<LieuCovoiturage> findByNom(String nom);
}
