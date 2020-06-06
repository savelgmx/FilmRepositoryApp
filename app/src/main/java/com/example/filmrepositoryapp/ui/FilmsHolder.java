package com.example.filmrepositoryapp.ui;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.filmrepositoryapp.R;
import com.example.filmrepositoryapp.model.Film;


public class FilmsHolder extends RecyclerView.ViewHolder{
    private TextView mTitle;
    private TextView mReleaseDate;
    private TextView mDirectorsName;
    private TextView mRating;

    public FilmsHolder(View itemView) {

        super(itemView);
        mTitle = itemView.findViewById(R.id.tv_title);
        mReleaseDate = itemView.findViewById(R.id.tv_release_date);
        mDirectorsName =itemView.findViewById(R.id.tv_directors_name);
        mRating=itemView.findViewById(R.id.tv_rating);

    }

 /*   public void bind(Film item, FilmAdapter.OnItemClickListener onItemClickListener) {
        mTitle.setText(item.getFilm_name());
        mReleaseDate.setText(item.getRelease_date());

        if(onItemClickListener!= null){
            itemView.setOnClickListener(v->onItemClickListener.onItemClick(item));
        }
    }
*/
    public void bind(Film item) {
        mTitle.setText(item.getFilm_name());
        mReleaseDate.setText(item.getRelease_date());
        mDirectorsName.setText(item.getDirectors_name());
        mRating.setText(item.getRating());

    }

}

