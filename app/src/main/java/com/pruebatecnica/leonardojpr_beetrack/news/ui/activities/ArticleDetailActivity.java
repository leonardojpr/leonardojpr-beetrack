package com.pruebatecnica.leonardojpr_beetrack.news.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.webkit.WebView;

import com.pruebatecnica.leonardojpr_beetrack.R;
import com.pruebatecnica.leonardojpr_beetrack.database.Article;
import com.pruebatecnica.leonardojpr_beetrack.util.Constants;
import com.pruebatecnica.leonardojpr_beetrack.util.DBController;

import org.greenrobot.eventbus.EventBus;
/**
 * Created by Leonardojpr on 06/05/18.
 */
public class ArticleDetailActivity extends AppCompatActivity {

    AppCompatImageView btnBack;
    AppCompatImageView btnFavorite;
    WebView wbArticle;

    Article articleData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        btnBack = findViewById(R.id.btn_back);
        btnFavorite = findViewById(R.id.btn_favorite);
        wbArticle = findViewById(R.id.wv_article);

        clickBtnBack();
        clickBtnFavorite();

        if (getIntent().hasExtra(Constants.ARTICLE)) {
            articleData = DBController.getControler().getArticle(((Article) getIntent().getSerializableExtra(Constants.ARTICLE)).getId());
            wbArticle.loadUrl(articleData.getUrl());
            isArticleFavorite(articleData.getIsFavorite());
        } else {
            finish();
        }

    }

    public void clickBtnBack() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void clickBtnFavorite() {
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!articleData.getIsFavorite()) {
                    articleData.setIsFavorite(true);
                    DBController.getControler().createArticle(articleData);
                    isArticleFavorite(true);
                } else {
                    articleData.setIsFavorite(false);
                    isArticleFavorite(false);
                    DBController.getControler().createArticle(articleData);
                }

                EventBus.getDefault().post(articleData);
            }
        });
    }

    private void isArticleFavorite(boolean favorite) {
        if (favorite) {
            btnFavorite.setImageResource(R.drawable.ic_favorite);
        } else {
            btnFavorite.setImageResource(R.drawable.ic_favorite_border);
        }
    }
}
