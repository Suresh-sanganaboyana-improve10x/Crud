package com.example.crud.templates;

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
    Call<List<Template>> fetchTemplate();

    @POST("templates")
    Call<Template> addTemplate(@Body Template templates);

    @DELETE("templates/{id}")
    Call<Void> deleteTemplate(@Path("id") String id);

    @PUT("templates/{id}")
    Call<Void> editTemplate(@Path("id") String id, @Body Template templates);
}
