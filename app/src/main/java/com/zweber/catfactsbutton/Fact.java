package com.zweber.catfactsbutton;

import com.google.gson.annotations.SerializedName;

public class Fact {
    //Object that holds the fact text
    @SerializedName("fact")
    private String fact;

    public String getFact() {
        return fact;
    }
}
