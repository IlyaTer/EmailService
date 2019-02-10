package com.article.email.services;

import com.article.email.model.ArticleDto;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class RandomArticleService {

    @Value("${article.uri}")
    private String articleUri;

    private HttpHeaders headers;
    private HttpEntity<ArticleDto[]> entity;
    private RestTemplate rest;

    public RandomArticleService() {
    }

    {
        this.headers = new HttpHeaders();
        this.headers.setAccept(Arrays.asList(
                new MediaType[]{MediaType.APPLICATION_JSON}));

        this.entity = new HttpEntity<>(this.headers);

        this.rest = new RestTemplate();
    }//end not static block

    public ArticleDto getRandomArticle() {
        ResponseEntity<ArticleDto[]> response = rest.exchange(this.articleUri,
                HttpMethod.GET, entity, ArticleDto[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            ArticleDto[] arts = response.getBody();
            if (arts.length > 0) {
                return arts[(int) (Math.random() * arts.length)];
            } else {
                return null;
            }

        } else {
            return null;
        }
    }//end getRandomArticle

    public String getArticleUri() {
        return articleUri;
    }

    public void setArticleUri(String articleUri) {
        this.articleUri = articleUri;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public HttpEntity<ArticleDto[]> getEntity() {
        return entity;
    }

    public void setEntity(HttpEntity<ArticleDto[]> entity) {
        this.entity = entity;
    }

    public RestTemplate getRest() {
        return rest;
    }

    public void setRest(RestTemplate rest) {
        this.rest = rest;
    }

}//end RandomArticleService
