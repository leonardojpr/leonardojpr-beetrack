package com.pruebatecnica.leonardojpr_beetrack.news.di;

import com.pruebatecnica.leonardojpr_beetrack.app.di.AppComponent;
import com.pruebatecnica.leonardojpr_beetrack.news.ui.fragment.NewsFragment;

import dagger.Component;

@NewsScope
@Component(dependencies = {AppComponent.class}, modules = {NewsModule.class})
public interface NewsComponent {
    void inject(NewsFragment newsFragment);
}
