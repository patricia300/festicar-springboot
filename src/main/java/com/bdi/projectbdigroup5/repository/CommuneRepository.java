package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.Commune;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommuneRepository extends CrudRepository<Commune, String> {
}
