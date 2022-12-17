package com.example.crud.Api;

import com.example.crud.Constants;
import com.example.crud.messages.Message;
import com.example.crud.movies.Movie;
import com.example.crud.series.SeriesItem;
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

    @GET(Constants.SERIES_ITEMS_END_POINT)
    Call<List<SeriesItem>> fetchSeriesItems();

    @POST(Constants.SERIES_ITEMS_END_POINT)
    Call<SeriesItem> createSeriesItem(@Body SeriesItem seriesItem);

    @DELETE(Constants.SERIES_ITEMS_END_POINT + "/{id}")
    Call<Void> deleteSeriesItem(@Path("id") String id);

    @PUT(Constants.SERIES_ITEMS_END_POINT + "/{id}")
    Call<Void> editSeriesItem(@Path("id") String id, @Body SeriesItem seriesItem);

    @GET(Constants.TEMPLATES_END_POINT)
    Call<List<Template>> fetchTemplates();

    @POST(Constants.TEMPLATES_END_POINT)
    Call<Template> createTemplate(@Body Template templates);

    @DELETE(Constants.TEMPLATES_END_POINT + "/{id}")
    Call<Void> deleteTemplate(@Path("id") String id);

    @PUT(Constants.TEMPLATES_END_POINT + "/{id}")
    Call<Void> editTemplate(@Path("id") String id, @Body Template templates);
}
