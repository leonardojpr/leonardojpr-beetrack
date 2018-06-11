package com.pruebatecnica.leonardojpr_beetrack.news.mvp;

import com.pruebatecnica.leonardojpr_beetrack.database.Article;

import java.util.List;

/**
 * Created by Leonardojpr on 06/05/18.
 */
public class NewsContract {

    public interface InteractorResultListener {
        void onSucces(List<Article> articleDataList);

        void onFailure();

        void onConnectionFailure();
    }

    public interface Presenter<T> {
        void onCreateFragment(T view);

        void onDestroyFragment();

        void getNews(String secction);
    }

    public interface View {
        void loadNews(List<Article> articleDataList);

        void showProgress();

        void hideProgress();

        void showToastError();

        void showToastConnectionError();
    }
}
