package com.example.crud.series;

import com.example.crud.Constants;
import com.example.crud.series.Series;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SeriesService {

    @GET(Constants.SERIES_END_POINT)
    Call<List<Series>> fetchData();

    @POST(Constants.SERIES_END_POINT)
    Call<Series> addSeries(@Body Series series);

    @DELETE(Constants.SERIES_END_POINT + "/{id}")
    Call<Void> deleteSeries(@Path("id") String id);

    @PUT(Constants.SERIES_END_POINT + "/{id}")
    Call<Void> editSeries(@Path("id") String id, @Body Series series);
}
