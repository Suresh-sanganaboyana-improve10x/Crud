package com.example.crud.Api;

import com.example.crud.Constants;
import com.example.crud.messages.Message;
import com.example.crud.movies.Movie;
import com.example.crud.series.Series;
import com.example.crud.templates.Template;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CrudService {

    @GET(Constants.MESSAGES_END_POINT)
    Call<List<Message>> fetchMessages();

    @POST(Constants.MESSAGES_END_POINT)
    Call<Message> createMessage(@Body Message message);

    @DELETE(Constants.MESSAGES_END_POINT + "/{id}")
    Call<Void> deleteMessage(@Path("id") String id);

    @PUT(Constants.MESSAGES_END_POINT + "/{id}")
    Call<Void> editMessage(@Path("id") String id, @Body Message message);

    @GET(Constants.MOVIES_END_POINT)
    Call<List<Movie>> fetchMovies();

    @POST(Constants.MOVIES_END_POINT)
    Call<Movie> createMovie(@Body Movie movie);

    @DELETE(Constants.MOVIES_END_POINT + "/{id}")
    Call<Void> deleteMovie(@Path("id") String id);

    @PUT(Constants.MOVIES_END_POINT + "/{id}")
    Call<Void> editMovie(@Path("id") String id, @Body Movie movie);

    @GET(Constants.SERIES_END_POINT)
    Call<List<Series>> fetchSeries();

    @POST(Constants.SERIES_END_POINT)
    Call<Series> addSeries(@Body Series series);

    @DELETE(Constants.SERIES_END_POINT + "/{id}")
    Call<Void> deleteSeries(@Path("id") String id);

    @PUT(Constants.SERIES_END_POINT + "/{id}")
    Call<Void> editSeries(@Path("id") String id, @Body Series series);

    @GET(Constants.TEMPLATES_END_POINT)
    Call<List<Template>> fetchTemplate();

    @POST(Constants.TEMPLATES_END_POINT)
    Call<Template> addTemplate(@Body Template templates);

    @DELETE(Constants.TEMPLATES_END_POINT + "/{id}")
    Call<Void> deleteTemplate(@Path("id") String id);

    @PUT(Constants.TEMPLATES_END_POINT + "/{id}")
    Call<Void> editTemplate(@Path("id") String id, @Body Template templates);
}