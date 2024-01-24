package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.dto.ArticleRequestBodyDto;
import com.bdi.projectbdigroup5.model.Article;
import com.bdi.projectbdigroup5.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class ArticleController {
    private ArticleService articleService;
    @PostMapping("/article")
    public Article createArticle(@RequestBody ArticleRequestBodyDto requestBodyDto){
        return this.articleService.saveArticle(requestBodyDto);
    }
}
