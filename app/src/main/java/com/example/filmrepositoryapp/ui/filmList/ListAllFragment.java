package com.example.filmrepositoryapp.ui.filmList;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmrepositoryapp.R;
import com.example.filmrepositoryapp.ui.common.BaseFragment;
import com.example.filmrepositoryapp.ui.common.BaseViewModel;
import com.example.filmrepositoryapp.ui.filmDetail.FilmDetailDialogFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAllFragment extends BaseFragment implements IOnItemClickListener {
    View view;
    @BindView(R.id.rvFilmList)
    RecyclerView mRecyclerView;

    @BindView(R.id.ll_error)
    LinearLayout mErrorLayout;

    @Inject
    protected FilmListAdapter mAdapter;

    @Inject
    protected BaseViewModel mViewModel;


    public static ListAllFragment newInstance(String parentScopeName) {

        Bundle args = new Bundle();
        args.putString(KEY_PARENT_SCOPE_NAME, parentScopeName);

        ListAllFragment fragment = new ListAllFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fr_film_list, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        mRecyclerView.setAdapter(mAdapter);

        mViewModel.getFilmList().observe(this, list -> mAdapter.submitList(list));

        mViewModel.getIsEmpty().observe(this, isEmpty -> {
            if (isEmpty != null && !isEmpty) {
                mRecyclerView.setVisibility(View.VISIBLE);
                mErrorLayout.setVisibility(View.GONE);
            } else {
                mRecyclerView.setVisibility(View.GONE);
                mErrorLayout.setVisibility(View.VISIBLE);
            }
        });
    }


    @Override
    public void OnItemClick(long id) {
        FilmDetailDialogFragment filmDetailDialogFragment = FilmDetailDialogFragment.newInstance(id);
        filmDetailDialogFragment.show(getActivity().getSupportFragmentManager(), "filmDetailDialogFragment");
    }

    @Override
    public boolean OnItemLongClick(long id) {
        new AlertDialog.Builder(getContext())
                .setMessage(R.string.delete_message)
                .setNegativeButton(R.string.no_label, null)
                .setPositiveButton(R.string.yes_label, (dialogInterface, i) -> mViewModel.deleteItem(id))
                .create()
                .show();
        return true;
    }


}
