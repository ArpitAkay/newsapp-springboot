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
    public static final String REMOVED = "[Removed]";

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

        if(news == null) return new News();

        news.getArticles().removeIf(article -> {
                return article.getAuthor() == null || article.getAuthor().equals(REMOVED)
                || article.getTitle() == null || article.getTitle().equals(REMOVED)
                || article.getUrl() == null || article.getUrl().equals(REMOVED)
                || article.getUrlToImage() == null || article.getUrlToImage().equals(REMOVED)
                || article.getPublishedAt() == null
                || article.getContent() == null || article.getContent().equals(REMOVED);
                }
        );

        return news;
    }
}
