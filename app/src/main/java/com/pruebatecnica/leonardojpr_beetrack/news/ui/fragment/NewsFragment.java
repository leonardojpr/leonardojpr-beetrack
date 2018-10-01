package com.pruebatecnica.leonardojpr_beetrack.news.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pruebatecnica.leonardojpr_beetrack.R;
import com.pruebatecnica.leonardojpr_beetrack.app.App;
import com.pruebatecnica.leonardojpr_beetrack.base.BaseFragment;
import com.pruebatecnica.leonardojpr_beetrack.database.Article;
import com.pruebatecnica.leonardojpr_beetrack.news.di.DaggerNewsComponent;
import com.pruebatecnica.leonardojpr_beetrack.news.di.NewsModule;
import com.pruebatecnica.leonardojpr_beetrack.news.mvp.NewsContract;
import com.pruebatecnica.leonardojpr_beetrack.news.mvp.NewsPresenter;
import com.pruebatecnica.leonardojpr_beetrack.news.ui.activities.ArticleDetailActivity;
import com.pruebatecnica.leonardojpr_beetrack.news.ui.adapter.NewsAdapter;
import com.pruebatecnica.leonardojpr_beetrack.util.Commons;
import com.pruebatecnica.leonardojpr_beetrack.util.Constants;
import com.pruebatecnica.leonardojpr_beetrack.util.listeners.OnNewsListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Leonardojpr on 06/05/18.
 */
public class NewsFragment extends BaseFragment implements NewsContract.View, OnNewsListener {

    RecyclerView rvArticle;
    SwipeRefreshLayout swipeArticle;
    ProgressBar progressbarArticle;

    LinearLayoutManager mLayoutManager;
    NewsAdapter newsAdapter;

    private String section;

    @Inject
    public NewsPresenter presenter;

    public static NewsFragment getInstance(String section) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.SECTION, section);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        section = getArguments().getString(Constants.SECTION);
        if (section.matches(Constants.NewsSection.FAVORITE))
            EventBus.getDefault().register(this);
        initComponent();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvArticle = view.findViewById(R.id.rv_article);
        swipeArticle = view.findViewById(R.id.swiperefresh_article);
        progressbarArticle = view.findViewById(R.id.progressbar_article);

        presenter.onCreateFragment(this);

        initRecyclerView();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroyFragment();
    }

    @Override
    public void loadNews(List<Article> articleDataList) {
        newsAdapter.updateAll(articleDataList);
    }

    @Override
    public void showProgress() {
        progressbarArticle.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressbarArticle.setVisibility(View.GONE);
        swipeArticle.setRefreshing(false);
    }

    @Override
    public void showToastConnectionError() {
        Toast.makeText(getContext(), Commons.getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToastError() {
        Toast.makeText(getContext(), Commons.getString(R.string.try_again), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMoreListener(Article articleData) {
        Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
        intent.putExtra(Constants.ARTICLE, articleData);
        getActivity().startActivity(intent);
    }

    @Override
    protected void initComponent() {
        DaggerNewsComponent.builder().
                appComponent(App.get().component())
                .newsModule(new NewsModule(this))
                .build().inject(NewsFragment.this);
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);

        rvArticle.setLayoutManager(mLayoutManager);
        List<Article> itemList = new ArrayList<>();
        newsAdapter = new NewsAdapter(getActivity(), itemList, this);
        rvArticle.setAdapter(newsAdapter);

        swipeArticle.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initRecyclerView();
                presenter.getNews(section);
            }
        });

        presenter.getNews(section);

    }

    @Subscribe
    public void onMessageEvent(Article article) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initRecyclerView();
            }
        }, 0);

    }
}
