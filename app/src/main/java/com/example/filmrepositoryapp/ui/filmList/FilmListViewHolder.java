package com.example.filmrepositoryapp.ui.filmList;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.filmrepositoryapp.R;
import com.example.filmrepositoryapp.model.Film;
import com.example.filmrepositoryapp.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FilmListViewHolder extends RecyclerView.ViewHolder {
    private View view;
    private long mId;

    @BindView(R.id.tvName)
    TextView mTvName;
    @BindView(R.id.tvDirector)
    TextView mTvDirector;
    @BindView(R.id.tvYear)
    TextView mTvYear;
    @BindView(R.id.tvRate)
    TextView mTvRate;
    @BindView(R.id.imageURL)
    ImageView mImageURL;

    final Context context;



    public FilmListViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        ButterKnife.bind(this, view);
        this.context = itemView.getContext();
    }

    public void bind(Film film) {
        mTvName.setText(film.getFilmName());
        mTvDirector.setText(film.getDirectors_name());
        mTvYear.setText(String.valueOf(film.getRelease_date()));
        mTvRate.setText(StringUtils.rateToString(film.getRating()));
        mId = film.getId();

        //here we load background image with Glide help
        if(film.getImageUrl()!=null){
            Glide.with(context)
                    .load(film.getImageUrl())
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mImageURL);

        }




    }

    public void setOnItemClickListener(final IOnItemClickListener listener) {
        view.setOnLongClickListener(view -> listener != null && listener.OnItemLongClick(mId));
        view.setOnClickListener(view -> {
            if (listener != null) listener.OnItemClick(mId);
        });
    }

}
