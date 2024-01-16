package com.restapi.newsapp.controller;

import com.restapi.newsapp.model.News;
import com.restapi.newsapp.service.NewsService;
import com.restapi.newsapp.util.Endpoints;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.BASE_URL)
public class NewsController {

    private final NewsService newsService;

    public NewsController(
            NewsService newsService
    ) {
        this.newsService = newsService;
    }

    @GetMapping(Endpoints.NEWS)
    public News getAllNews(
            @RequestParam(name = "searchQuery", required = true) String searchQuery,
            @RequestParam(name = "from", required = false) String from,
            @RequestParam(name = "to", required = false) String to,
            @RequestParam(name = "language", defaultValue = "en", required = false) String language,
            @RequestParam(name = "sortBy", defaultValue = "publishedAt", required = false) String sortBy,
            @RequestParam(name = "pageSize", defaultValue = "100", required = false) String pageSize,
            @RequestParam(name = "page", defaultValue = "1", required = false) String page
    ) {
        return newsService.getAllNews(
                searchQuery,
                from,
                to,
                language,
                sortBy,
                pageSize,
                page
        );
    }
}
