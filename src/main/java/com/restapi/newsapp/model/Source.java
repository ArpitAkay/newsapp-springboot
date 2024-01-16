package com.restapi.newsapp.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Source {
    private String id;
    private String name;
}
