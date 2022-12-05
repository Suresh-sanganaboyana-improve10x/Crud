package com.example.crud.templates;

import com.example.crud.templates.Templates;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TemplatesService {

    @GET("templates")
    Call<List<Templates>> fetchTemplate();

    @POST("templates")
    Call<Templates> addTemplate(@Body Templates templates);

    @DELETE("templates/{id}")
    Call<Void> deleteTemplate(@Path("id") String id);

    @PUT("templates/{id}")
    Call<Void> editTemplate(@Path("id") String id, @Body Templates templates);
}
