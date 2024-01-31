package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.Festivalier;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FestivalierRepository extends CrudRepository <Festivalier, String>, PagingAndSortingRepository<Festivalier, String> {

    Page<Festivalier> findAll(Pageable pageable);

}
