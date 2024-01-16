package com.restapi.newsapp.service_impl;

import com.restapi.newsapp.model.News;
import com.restapi.newsapp.service.NewsService;
import com.restapi.newsapp.util.Endpoints;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsServiceImpl implements NewsService {

    private final RestTemplate restTemplate;
    private final String apiKey;

    public NewsServiceImpl(
            RestTemplate restTemplate,
            @Value("${newsapi.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }

    @Override
    public News getAllNews(
            String searchQuery,
            String from,
            String to,
            String language,
            String sortBy,
            String pageSize,
            String page
    ) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Api-Key", apiKey);

        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        //set request parameters
        String url = Endpoints.NEWS_API_BASE_URL
                + "?q=" + searchQuery
                + "&from=" + from
                + "&to=" + to
                + "&language=" + language
                + "&sortBy=" + sortBy
                + "&pageSize=" + pageSize
                + "&page=" + page;

        News news = restTemplate.exchange(url, HttpMethod.GET, httpEntity, News.class).getBody();

        news.getArticles().removeIf(article -> {
                return article.getAuthor() == null
                || article.getTitle() == null || article.getTitle().equals("[Removed]")
                || article.getUrl() == null || article.getDescription().equals("[Removed]")
                || article.getUrlToImage() == null
                || article.getPublishedAt() == null
                || article.getContent() == null;
                }
        );

        return news;
    }
}
