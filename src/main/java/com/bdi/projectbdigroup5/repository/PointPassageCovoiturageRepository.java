package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.PointPassageCovoiturage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PointPassageCovoiturageRepository extends CrudRepository<PointPassageCovoiturage, Long> { }
