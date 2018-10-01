package com.pruebatecnica.leonardojpr_beetrack.news.mvp;

import com.pruebatecnica.leonardojpr_beetrack.network.api.ArticleApi;
import com.pruebatecnica.leonardojpr_beetrack.network.data.ArticleData;
import com.pruebatecnica.leonardojpr_beetrack.network.response.NewsResponse;
import com.pruebatecnica.leonardojpr_beetrack.util.DBController;
import com.pruebatecnica.leonardojpr_beetrack.util.Factory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Leonardojpr on 06/05/18.
 */
public class NewsInteractor {

    private static final String TAG = NewsInteractor.class.getSimpleName();
    ArticleApi articleApi;

    public NewsInteractor(ArticleApi articleApi) {
        this.articleApi = articleApi;
    }

    public void getNewsList(final NewsContract.InteractorResultListener interactorResultListener) {
        articleApi.getNews("us", "f771430ff4904757825953a862fc7de6")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<NewsResponse>() {
                    @Override
                    public void accept(NewsResponse newsResponse) throws Exception {
                        for (ArticleData articleData : newsResponse.getArticles()) {
                            if (DBController.getControler().existArticle(articleData.getTitle())) {
                                DBController.getControler().createArticle(Factory.getArticleData(articleData));
                            }
                        }
                        interactorResultListener.onSucces(DBController.getControler().getArticleList());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        interactorResultListener.onFailure();
                    }
                });

    }

    public void getArticleFavorites(NewsContract.InteractorResultListener interactorResultListener) {
        interactorResultListener.onSucces(DBController.getControler().getArticleFavoriteList());
    }
}
