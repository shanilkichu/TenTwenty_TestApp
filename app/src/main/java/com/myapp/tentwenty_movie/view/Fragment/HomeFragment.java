package com.myapp.tentwenty_movie.view.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.tentwenty_movie.R;
import com.myapp.tentwenty_movie.model.listAllMovies.ListAllMoviesModel;
import com.myapp.tentwenty_movie.model.listAllMovies.Result;
import com.myapp.tentwenty_movie.utils.CustomDialog;
import com.myapp.tentwenty_movie.utils.Util;
import com.myapp.tentwenty_movie.view.Adapter.MoviewList_Adapter;
import com.myapp.tentwenty_movie.viewmodel.ListAllMovies_viewModel;

import java.time.format.ResolverStyle;
import java.util.ArrayList;


public class HomeFragment extends Fragment {

    Context context;
    Activity activity;
    private CustomDialog customDialog;
    private ArrayList<Result> resultData;
    private ArrayList<Result> temp_list=new ArrayList<>();
    MoviewList_Adapter adapter;
    RecyclerView Recy_listMovies;
    SearchView svMovies;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getContext();
        this.activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initView(view);
        return view;

    }

    private void initView(View view) {
        customDialog = new CustomDialog(activity);
        Recy_listMovies = view.findViewById(R.id.recy_MoviesList);
        svMovies=view.findViewById(R.id.toolbarSearch);
        svMovies.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
        ListAllMovies_viewModel viewModel = new ViewModelProvider(this).get(ListAllMovies_viewModel.class);
        if(Util.isNetworkAvailable(activity)) {
        viewModel.getList();
        }
        else
        {
            Toast.makeText(activity, "Please check your internet connection", Toast.LENGTH_SHORT).show();
        }

        //
        resultData = new ArrayList<>();
        adapter = new MoviewList_Adapter(resultData, activity);
        Recy_listMovies.setHasFixedSize(true);
        Recy_listMovies.setLayoutManager(new LinearLayoutManager(activity));
        Recy_listMovies.setAdapter(adapter);

        viewModel.getMoviesListMutableLiveData().observe(requireActivity(), new Observer<ListAllMoviesModel>() {
            @Override
            public void onChanged(ListAllMoviesModel listAllMoviesModel) {

                if (listAllMoviesModel.getResults().size() != 0) {
                    resultData.clear();
                    temp_list.clear();
                    resultData.addAll(listAllMoviesModel.getResults());
                    temp_list.addAll(listAllMoviesModel.getResults());
                    adapter.notifyDataSetChanged();
                    Recy_listMovies.setVisibility(View.VISIBLE);
                } else {
                    Recy_listMovies.setVisibility(View.GONE);
                }

            }
        });
        viewModel.getProgressObservablle().observe(requireActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    customDialog.show();
                } else {
                    customDialog.dismiss();
                }
            }
        });
        viewModel.getErrorREsponseMsg().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void filter(String newText)
    {
        ArrayList<Result> results=new ArrayList<>();
        for (Result result1 : temp_list){
            if (result1.getTitle().contains(newText)){
                results.add(result1);
            }
        }
        if (results.size()>0){
            resultData.clear();
            resultData.addAll(results);
            adapter.notifyDataSetChanged();
        }else if (results.size()==0){
            resultData.clear();
            resultData.addAll(temp_list);
            adapter.notifyDataSetChanged();
        }
    }

}
