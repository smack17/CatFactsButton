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

        catFact = findViewById(R.id.factText); //Text for the cat fact
        button = findViewById(R.id.button); //Button used to get new cat fact

        //Listens for button clicks (when user taps the button)
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catFact.setText(R.string.loading);
                //Retrofit builder object so that jsonPlaceHolderApi knows where to get JSON data from
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://catfact.ninja/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                //Gets JSON from the cat fact REST API using the properties in the retrofit builder
                JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

                //Gets data from the JsonPlaceHolderAPI. Fact is an object that holds the string data from the REST API
                Call<Fact> call = jsonPlaceHolderApi.getPosts();

                //Make the request to the REST API for the data
                call.enqueue(new Callback<Fact>() {

                    //If there is a response
                    @Override
                    public void onResponse(Call<Fact> call, Response<Fact> response) {
                        //If it isn't successful
                        if (!response.isSuccessful()) {
                            catFact.setText("Code: " + response.code());
                            return;
                        }

                        //If it is successful, change the fact text to the contents in the response
                        Fact data = response.body();
                        String fact = data.getFact();
                        catFact.setText(fact);
                    }

                    //If the call fails, set the text to the error message
                    @Override
                    public void onFailure(Call<Fact> call, Throwable t) {
                        catFact.setText(t.getMessage());
                    }
                });
            }
        });
    }
}
