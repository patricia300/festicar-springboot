package com.bdi.projectbdigroup5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleRequestBodyDto {
    private Long idPointPassage;
    private int quantite;
}
