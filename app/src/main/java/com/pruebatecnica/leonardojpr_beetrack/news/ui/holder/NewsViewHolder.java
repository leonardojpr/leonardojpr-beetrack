package com.pruebatecnica.leonardojpr_beetrack.news.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pruebatecnica.leonardojpr_beetrack.R;
import com.pruebatecnica.leonardojpr_beetrack.database.Article;
import com.pruebatecnica.leonardojpr_beetrack.util.Commons;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    View view;
    ImageView imgHeader;
    TextView txtTitle;
    TextView txtAuthor;
    TextView txtDate;
    TextView txtDescription;
    TextView txtMore;

    public static NewsViewHolder getInstance(ViewGroup parent) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false));
    }

    public NewsViewHolder(View itemView) {
        super(itemView);
        view = itemView;

        imgHeader = view.findViewById(R.id.img_header);
        txtTitle = view.findViewById(R.id.txt_title);
        txtAuthor = view.findViewById(R.id.txt_author);
        txtDate = view.findViewById(R.id.txt_date);
        txtDescription = view.findViewById(R.id.txt_description);
        txtMore = view.findViewById(R.id.txt_more);
    }

    public void setData(Article articleData) {

        txtTitle.setText(verifyString(articleData.getTitle()));
        txtAuthor.setText(Commons.getString(R.string.author) + ": " + verifyString(articleData.getAuthor()));
        txtDate.setText(verifyString(articleData.getPublishedAt()));
        txtDescription.setText(verifyString(articleData.getDescription()));
        Glide.with(view.getContext())
                .load(verifyString(articleData.getUrlToImage()))
                .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground))
                .into(imgHeader);
    }

    public void onClickMoreListener(View.OnClickListener onClickListener) {
        txtMore.setOnClickListener(onClickListener);
    }

    public void onClickItemListener(View.OnClickListener onClickListener) {
        view.setOnClickListener(onClickListener);
    }

    private String verifyString(String object) {
        if (object != null)
            return (String) object;

        return "";
    }
}
