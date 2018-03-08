package me.travisgray.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Article
{
    private Source source;

    public Source getSource() { return this.source; }

    public void setSource(Source source) { this.source = source; }

    private String author;

    public String getAuthor() { return this.author; }

    public void setAuthor(String author) { this.author = author; }

    private String title;

    public String getTitle() { return this.title; }

    public void setTitle(String title) { this.title = title; }

    private String description;

    public String getDescription() { return this.description; }

    public void setDescription(String description) { this.description = description; }

    private String url;

    public String getUrl() { return this.url; }

    public void setUrl(String url) { this.url = url; }

    private String urlToImage;

    public String getUrlToImage() { return this.urlToImage; }

    public void setUrlToImage(String urlToImage) { this.urlToImage = urlToImage; }

    private Date publishedAt;

    public Date getPublishedAt() { return this.publishedAt; }

    public void setPublishedAt(Date publishedAt) { this.publishedAt = publishedAt; }
}