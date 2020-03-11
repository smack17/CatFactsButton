package com.zweber.catfactsbutton;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("fact")
    Call<Fact> getPosts();

}
