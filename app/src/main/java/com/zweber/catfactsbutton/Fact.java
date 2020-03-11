package com.zweber.catfactsbutton;

import com.google.gson.annotations.SerializedName;

public class Fact {

    @SerializedName("fact")
    private String fact;

    public String getFact() {
        return fact;
    }
}
