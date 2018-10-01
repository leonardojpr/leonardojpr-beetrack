package com.pruebatecnica.leonardojpr_beetrack.util;

import com.pruebatecnica.leonardojpr_beetrack.database.Article;
import com.pruebatecnica.leonardojpr_beetrack.network.data.ArticleData;
/**
 * Created by Leonardojpr on 06/05/18.
 */
public class Factory {

    public static Article getArticleData(ArticleData articleData) {
        Article article = new Article();
        article.setId(System.currentTimeMillis());
        article.setIds(articleData.getSource().getId());
        article.setName(articleData.getSource().getName());
        article.setTitle(articleData.getTitle());
        article.setAuthor(articleData.getAuthor());
        article.setPublishedAt(articleData.getPublishedAt());
        article.setDescription(articleData.getDescription());
        article.setUrl(articleData.getUrl());
        article.setUrlToImage(articleData.getUrlToImage());
        article.setIsFavorite(false);

        return article;
    }

}
