package com.bdi.projectbdigroup5.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class PanierRequestBodyDto {
    private String emailFestivalier;
    private List<ArticleRequestBodyDto>  articles;
}
