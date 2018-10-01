package com.pruebatecnica.leonardojpr_beetrack.news.di;

import com.pruebatecnica.leonardojpr_beetrack.news.mvp.NewsInteractor;
import com.pruebatecnica.leonardojpr_beetrack.news.mvp.NewsPresenter;
import com.pruebatecnica.leonardojpr_beetrack.network.api.ArticleApi;
import com.pruebatecnica.leonardojpr_beetrack.news.ui.fragment.NewsFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsModule {

    private NewsFragment fragment;

    public NewsModule(NewsFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @NewsScope
    public NewsFragment provideFragment() {
        return fragment;
    }

    @Provides
    @NewsScope
    public NewsInteractor provideNewsInteractor(ArticleApi articleApi) {
        return new NewsInteractor(articleApi);
    }

    @Provides
    @NewsScope
    public NewsPresenter providePresenter(NewsInteractor interactor) {
        return new NewsPresenter(fragment, interactor);
    }
}
