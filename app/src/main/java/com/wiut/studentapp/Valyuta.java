package com.wiut.studentapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.wiut.studentapp.api.ApiClient;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class Valyuta extends AppCompatActivity {

    CoinListAdapter adapter;
    ArrayList<Modelitem> dataModalArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.valyuta);
        ListView listcoin = findViewById(R.id.listcoin);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        dataModalArrayList = new ArrayList<>();
        listcoin.setDivider(null);
        listcoin.setDividerHeight(20);

        adapter = new CoinListAdapter(dataModalArrayList,getApplicationContext());

        Call<List<Currency>> call = ApiClient.getUserService().userTokenRequest();
        call.enqueue(new Callback<List<Currency>>() {
            @Override
            public void onResponse(@NotNull Call<List<Currency>> call, @NotNull Response<List<Currency>> response) {
                if (response.isSuccessful()){
                    List<Currency> currency = response.body();
                    assert currency != null;
                    for (Currency cur : currency){
                        dataModalArrayList.add(new Modelitem(cur.getDate(),cur.getCcyNm_UZ(),cur.getRate()));
                    }
                    progressBar.setVisibility(View.GONE);
                    listcoin.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(@NotNull Call<List<Currency>> call, @NotNull Throwable t) {
                Toast.makeText(Valyuta.this, "Error", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
