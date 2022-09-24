package com.wiut.studentapp.api;

import com.wiut.studentapp.Currency;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface UserService {
    @GET("json")
    Call<List<Currency>> userTokenRequest();

}