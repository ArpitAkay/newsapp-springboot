package com.restapi.newsapp.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class News {
    private String status;
    private String totalResults;
    private List<Article> articles;
}
