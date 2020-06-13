package com.example.filmrepositoryapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.filmrepositoryapp.R;
import com.example.filmrepositoryapp.model.Film;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class FilmsAddFilmView extends LinearLayout implements FilmActivity.DialogContract {
    public FilmsAddFilmView(Context context) {
        super(context);
    }

    public FilmsAddFilmView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FilmsAddFilmView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    String filmName;
    String directorsName;

    int rating;
    int releaseDate;


    @BindView(R.id.tvFilmName)
    EditText textFilmName;

    @BindView(R.id.tvDirectorsName)
    EditText textDirectorsName;

    @BindView(R.id.edReleaseDate)
    EditText  textReleasedate;

    @BindView(R.id.edRating)
    EditText textRating;

    @OnTextChanged(R.id.tvFilmName)
    public void filmNameChanged(CharSequence _filmName) {
        filmName = _filmName.toString();
    }

    @OnTextChanged(R.id.tvDirectorsName)
    public void directorsNameChanged(CharSequence _directorsName) {
        directorsName = _directorsName.toString();
    }
    @OnTextChanged(R.id.edReleaseDate)
    public void releaseDateChanged(int _releaseDate) {
        releaseDate = _releaseDate;
    }

    @OnTextChanged(R.id.edRating)
    public void ratingChanged(int _rating) {
        releaseDate = _rating;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }


    @Override
    public String getFilmName() {
        return filmName;
    }

    @Override
    public String getDirectorsName() {
        return directorsName;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public int getReleaseDate() {
        return releaseDate;
    }

    @Override
    public void bind(Film film) {

        String _filmName = film.getFilm_name();
        String _directorsName =film.getDirectors_name();
        int _rating = film.getRating();
        int _releaseDate = film.getRelease_date();

        textFilmName.setText(_filmName);
        textDirectorsName.setText(_directorsName);
        textRating.setText(String.valueOf(_rating) );
        textReleasedate.setText(String.valueOf(_releaseDate)  );

        filmName = _filmName;
        directorsName = _directorsName;
        rating= _rating;
        releaseDate= _releaseDate;


    }
}
