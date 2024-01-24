package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.Festival;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface FestivalRepository extends CrudRepository<Festival, Long>, PagingAndSortingRepository<Festival, Long>{
    
    Page<Festival> findAll(Pageable pageable);
}
