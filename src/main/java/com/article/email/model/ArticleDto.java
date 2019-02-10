package com.article.email.model;

import java.util.List;


public class ArticleDto {
    
    private Long id;
    private String name;
    private String topic;
    private String text;
    private String author;
    private List<CommentDto> comments;

    public ArticleDto() {
    }

    public ArticleDto(Long id, String name, String topic, String text, String author, List<CommentDto> comments) {
        this.id = id;
        this.name = name;
        this.topic = topic;
        this.text = text;
        this.author = author;
        this.comments = comments;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }
    
    
}
