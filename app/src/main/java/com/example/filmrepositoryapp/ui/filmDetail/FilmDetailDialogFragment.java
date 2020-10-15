package com.example.filmrepositoryapp.ui.filmDetail;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.filmrepositoryapp.R;
import com.example.filmrepositoryapp.di.FilmDetailDialogFragmentModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import toothpick.Scope;
import toothpick.Toothpick;

public class FilmDetailDialogFragment extends DialogFragment {

    private Scope mScope;
    private static final String KEY_FILM_ID = "FilmDetailDialogFragment.KeyFilmId";

    @Inject
    protected FilmDetailViewModel mViewModel;

    @BindView(R.id.tvTitle)
    protected TextView tvTitle;

    @BindView(R.id.etName)
    protected EditText etName;

    @BindView(R.id.etDirector)
    protected EditText etDirector;

    @BindView(R.id.etYear)
    protected EditText etYear;

    @BindView(R.id.etRate)
    protected EditText etRate;


    private DialogInterface.OnClickListener mOnClickListener = (dialogInterface, i) -> {
        mViewModel.apply(etName.getText().toString(),
                etDirector.getText().toString(),
                etYear.getText().toString(),
                etRate.getText().toString());


    };

    public static FilmDetailDialogFragment newInstance(long id) {

        Bundle args = new Bundle();
        args.putLong(KEY_FILM_ID, id);
        FilmDetailDialogFragment fragment = new FilmDetailDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        toothpickInject();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fr_detail_dialog_fragment, null);
        initUI(view);

        builder.setView(view)
                .setPositiveButton(R.string.btn_save_label, mOnClickListener)
                .setNegativeButton(R.string.btn_cancel_label, null);

        return builder.create();
    }

    private void toothpickInject() {
        long id = -1;
        if (getArguments() != null) {
            id = getArguments().getLong(KEY_FILM_ID, -1);
        }

        mScope = Toothpick.openScopes("Application", this.getClass().getSimpleName());
        mScope.installModules(new FilmDetailDialogFragmentModule(this, id));
        Toothpick.inject(this, mScope);
    }


    @Override
    public void onDetach() {
        Toothpick.closeScope(this.getClass().getSimpleName());
        super.onDetach();
    }

    private void initUI(View view) {
        ButterKnife.bind(this, view);

        tvTitle.setText(mViewModel.getTitleId());
        mViewModel.getName().observe(this, str -> etName.setText(str));
        mViewModel.getDirector().observe(this, str -> etDirector.setText(str));
        mViewModel.getYear().observe(this, str -> etYear.setText(str));
        mViewModel.getRating().observe(this, str -> etRate.setText(str));
    }


}
