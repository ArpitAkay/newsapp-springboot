package com.restapi.newsapp.service;

import com.restapi.newsapp.model.News;

public interface NewsService {
    News getAllNews(
            String searchQuery,
            String from,
            String to,
            String language,
            String sortBy,
            String pageSize,
            String page
    );
}
