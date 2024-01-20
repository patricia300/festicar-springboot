package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.SousDomaine;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface SousDomaineRepository extends CrudRepository<SousDomaine, String>{
    
}
