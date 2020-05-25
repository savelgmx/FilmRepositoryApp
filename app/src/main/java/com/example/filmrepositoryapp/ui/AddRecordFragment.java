package com.example.filmrepositoryapp.ui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.filmrepositoryapp.R;
import com.example.filmrepositoryapp.model.Film;
import com.example.filmrepositoryapp.model.FilmRepository;



public class AddRecordFragment extends Fragment {

    private AutoCompleteTextView mFilmName;
    private EditText mReleaseDate;
    private AutoCompleteTextView mDirectorsName;
    private EditText mRating;


    private Button mOnSaveRecord;
    private Button mOnCancelAddRecord;

    private FilmRepository mFilmRepository;


    public static Fragment newInstance() {
        return new AddRecordFragment();
    }
     public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        super.onCreate(savedInstanceState);
         mFilmRepository = new FilmRepository();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fr_add_record, container, false);

        mFilmName =v.findViewById(R.id.tvFilmName);
        mDirectorsName =v.findViewById(R.id.tvDirectorsName);
        mReleaseDate= v.findViewById(R.id.edReleaseDate);
        mRating=v.findViewById(R.id.edRating);

        mOnSaveRecord = v.findViewById(R.id.btnSave);
        mOnCancelAddRecord = v.findViewById(R.id.btnCancel);

        mOnSaveRecord.setOnClickListener(mOnSaveRecordListener);
        mOnCancelAddRecord.setOnClickListener(mOnCancelAddRecordListener);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private View.OnClickListener mOnSaveRecordListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mFilmRepository.insertItem(getNewFilm());
        }
    };

    private View.OnClickListener mOnCancelAddRecordListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            mFilmName.setText(" ");
            mDirectorsName.setText(" ");
            mRating.setText(0);
        }
    };

    private Film getNewFilm(){
        //здесь сохранаяем добавленную запись
        Film film = new Film();
        film.setFilm_name(mFilmName.getText().toString());
        film.setRelease_date( Integer.parseInt(mReleaseDate.getText().toString()));
        film.setDirectors_name(mDirectorsName.getText().toString());
        film.setRating(Integer.parseInt(mRating.getText().toString()));
        return film;
    }

}
