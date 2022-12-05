package com.example.crud.messages;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MessageService {

    @GET("sureshMessageHistory")
    Call<List<Messages>> fetchMessages();

    @POST("sureshMessageHistory")
    Call<Messages> createMessages(@Body Messages messages);

    @DELETE("sureshMessageHistory/{id}")
    Call<Void> deleteMessage(@Path("id") String id);

    @PUT("sureshMessageHistory/{id}")
    Call<Void> editMessage(@Path("id") String id, @Body Messages messages);
}
