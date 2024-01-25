package com.bdi.projectbdigroup5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PanierRequestBodyDto {
    private String emailFestivalier;
    private List<ArticleRequestBodyDto>  articles;
}
