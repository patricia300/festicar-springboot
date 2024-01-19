package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
}
