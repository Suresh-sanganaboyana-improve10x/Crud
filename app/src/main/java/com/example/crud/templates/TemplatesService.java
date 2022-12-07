package com.example.crud.templates;

import com.example.crud.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TemplatesService {

    @GET(Constants.TEMPLATES_END_POINT)
    Call<List<Template>> fetchTemplate();

    @POST(Constants.TEMPLATES_END_POINT)
    Call<Template> addTemplate(@Body Template templates);

    @DELETE(Constants.TEMPLATES_END_POINT + "/{id}")
    Call<Void> deleteTemplate(@Path("id") String id);

    @PUT(Constants.TEMPLATES_END_POINT + "/{id}")
    Call<Void> editTemplate(@Path("id") String id, @Body Template templates);
}
