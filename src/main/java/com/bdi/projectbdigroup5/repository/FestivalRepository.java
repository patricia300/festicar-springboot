package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.Festival;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface FestivalRepository extends CrudRepository<Festival, Long>{
    
}
