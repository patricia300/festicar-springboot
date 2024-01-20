package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.Domaine;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface DomaineRepository extends CrudRepository<Domaine, String>{
    
}
