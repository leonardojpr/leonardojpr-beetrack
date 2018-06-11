package com.pruebatecnica.leonardojpr_beetrack.news.mvp;

import com.pruebatecnica.leonardojpr_beetrack.database.Article;
import com.pruebatecnica.leonardojpr_beetrack.util.Constants;

import java.util.List;

/**
 * Created by Leonardojpr on 06/05/18.
 */
public class NewsPresenter implements NewsContract.Presenter<NewsContract.View>, NewsContract.InteractorResultListener {

    private NewsContract.View view;
    private NewsInteractor newsInteractor;

    public NewsPresenter(NewsContract.View view, NewsInteractor newsInteractor) {
        this.view = view;
        this.newsInteractor = newsInteractor;
    }

    @Override
    public void onCreateFragment(NewsContract.View view) {
        this.view = view;
    }

    @Override
    public void onDestroyFragment() {
        view = null;
    }

    @Override
    public void getNews(String secction) {
        view.showProgress();
        if (secction.matches(Constants.NewsSection.ARTICLE))
            newsInteractor.getNewsList(this);
        else
            newsInteractor.getArticleFavorites(this);
    }

    @Override
    public void onSucces(List<Article> articleDataList) {
        if (isViewNull())
            return;

        view.hideProgress();
        view.loadNews(articleDataList);
    }

    @Override
    public void onFailure() {
        if (isViewNull())
            return;

        view.hideProgress();
        view.showToastError();
    }

    @Override
    public void onConnectionFailure() {
        if (isViewNull())
            return;

        view.hideProgress();
        view.showToastConnectionError();
    }

    private boolean isViewNull() {
        return view == null;
    }
}
