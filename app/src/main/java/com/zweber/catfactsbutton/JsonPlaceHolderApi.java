package com.zweber.catfactsbutton;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    //Gets data from the REST API
    @GET("fact")
    Call<Fact> getPosts();

}
