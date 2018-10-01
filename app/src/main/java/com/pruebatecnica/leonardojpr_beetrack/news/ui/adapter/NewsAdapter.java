package com.pruebatecnica.leonardojpr_beetrack.news.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.pruebatecnica.leonardojpr_beetrack.database.Article;
import com.pruebatecnica.leonardojpr_beetrack.news.ui.holder.NewsViewHolder;
import com.pruebatecnica.leonardojpr_beetrack.util.listeners.OnNewsListener;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private Context context;
    private List<Article> articleDataList;
    private OnNewsListener onNewsListener;

    public NewsAdapter(Context context, List<Article> articleDataList, OnNewsListener onNewsListener) {
        this.context = context;
        this.articleDataList = articleDataList;
        this.onNewsListener = onNewsListener;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return NewsViewHolder.getInstance(parent);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        configureNewsViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return articleDataList.size();
    }

    private void configureNewsViewHolder(NewsViewHolder newsViewHolder, final int position) {
        newsViewHolder.setData(articleDataList.get(position));
        newsViewHolder.onClickItemListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   onNewsListener.onMoreListener(articleDataList.get(position));
                                               }
                                           }
        );

        newsViewHolder.onClickMoreListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   onNewsListener.onMoreListener(articleDataList.get(position));
                                               }
                                           }
        );
    }

    public void updateAll(List<Article> articleDataList) {
        this.articleDataList = articleDataList;
        notifyDataSetChanged();
    }
}
