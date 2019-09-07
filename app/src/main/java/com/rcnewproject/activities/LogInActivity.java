package com.rcnewproject.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.rcnewproject.R;
import com.rcnewproject.adapters.MoviesAdapter;
import com.rcnewproject.interfaces.NetworkListener;
import com.rcnewproject.models.MoviesResponse;
import com.rcnewproject.network.ApiInterface;
import com.rcnewproject.network.RestApi;
import com.rcnewproject.utils.AppUtils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends BaseActivity implements NetworkListener {
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        progressBar = findViewById(R.id.bar);
        recyclerView = findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        hitPopularMoviesAPI();
    }

    /*
     * Hit login Api through email and password
     * */
    private void hitPopularMoviesAPI() {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = RestApi.createService(LogInActivity.this, ApiInterface.class);
        final HashMap params = new HashMap<>();
        params.put("api_key", "26fd842346fe155033058e443bb094c3");
        Call<MoviesResponse> call = apiInterface.getTopRatedMovies(params);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(new MoviesAdapter(response.body().getResults(),
                        getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onSuccess(int responseCode, String response, int requestCode) {
        progressBar.setVisibility(View.GONE);
    }

    public void onError(String response, int requestCode) {
        progressBar.setVisibility(View.GONE);
        AppUtils.showToast(LogInActivity.this, response + "");
    }

    @Override
    public void onFailure() {
        progressBar.setVisibility(View.GONE);
        AppUtils.showToast(LogInActivity.this, "Failure");
    }
}
