package com.example.filmrepositoryapp.ui;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.filmrepositoryapp.FilmPresenter;
import com.example.filmrepositoryapp.R;
import com.example.filmrepositoryapp.model.Film;
import butterknife.BindView;
import butterknife.ButterKnife;


public class FilmsViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.card_films)
    CardView card;

    @BindView(R.id.text_film_name)
    TextView textFilmName;

    @BindView(R.id.text_directors_name)
    TextView textDirectorsName;

    @BindView(R.id.text_release_date)
    TextView textReleaseDate;
    @BindView(R.id.text_rating)
    TextView textRating;

    @BindView(R.id.image_background)
    ImageView imageBackground;

    final Context context;
    final FilmPresenter filmPresenter;

    public FilmsViewHolder(View itemView) {
        // standard view holder pattern with ButterKnife view injection

        super(itemView);
        this.context = itemView.getContext();
        this.filmPresenter = FilmPresenter.getService(context);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Film film) {
        // cast the generic view holder to our specific one
        // set the title and the snippet
        final long id = film.getId();
        textFilmName.setText(film.getFilm_name());
        textDirectorsName.setText(film.getDirectors_name());
        textReleaseDate.setText(film.getRelease_date());
        textRating.setText(film.getRating());
        //update single match from realm
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filmPresenter.showEditDialog(film);
            }
        });

    }
}
