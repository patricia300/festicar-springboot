package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
