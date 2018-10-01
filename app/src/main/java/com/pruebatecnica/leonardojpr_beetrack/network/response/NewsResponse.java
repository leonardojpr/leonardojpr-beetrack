package com.pruebatecnica.leonardojpr_beetrack.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pruebatecnica.leonardojpr_beetrack.network.data.ArticleData;

import java.util.List;

/**
 * Created by Leonardojpr on 06/05/18.
 */
public class NewsResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("articles")
    @Expose
    private List<ArticleData> articles = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticleData> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleData> articleData) {
        this.articles = articleData;
    }

}
