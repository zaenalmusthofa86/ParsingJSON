package com.zaenal.jsonzaenal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.zaenal.jsonzaenal.api.ApiClient;
import com.zaenal.jsonzaenal.api.BreachResponse;
import com.zaenal.jsonzaenal.recycler.BreachAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    BreachAdapter breachAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.data_breach);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        breachAdapter = new BreachAdapter();
        getAllBreach();
    }

    public void getAllBreach(){
        Call<List<BreachResponse>> breachList = ApiClient.getBreachService().getAllBreach();

        breachList.enqueue(new Callback<List<BreachResponse>>() {
            @Override
            public void onResponse(Call<List<BreachResponse>> call, Response<List<BreachResponse>> response) {
                if(response.isSuccessful()){
                    List<BreachResponse> breachResponses = response.body();
                    breachAdapter.setData(breachResponses);
                    recyclerView.setAdapter(breachAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<BreachResponse>> call, Throwable t) {
                Log.e("gagal", t.getLocalizedMessage());
            }
        });
    }
}