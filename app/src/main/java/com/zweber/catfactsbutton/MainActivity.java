package com.zweber.catfactsbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView catFact;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        catFact = findViewById(R.id.factText);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catFact.setText(R.string.loading);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://catfact.ninja/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
                Call<Fact> call = jsonPlaceHolderApi.getPosts();

                call.enqueue(new Callback<Fact>() {
                    @Override
                    public void onResponse(Call<Fact> call, Response<Fact> response) {
                        if (!response.isSuccessful()) {
                            catFact.setText("Code: " + response.code());
                            return;
                        }

                        Fact data = response.body();
                        String fact = data.getFact();
                        catFact.setText(fact);
                    }

                    @Override
                    public void onFailure(Call<Fact> call, Throwable t) {
                        catFact.setText(t.getMessage());
                    }
                });
            }
        });
    }
}
