package com.pruebatecnica.leonardojpr_beetrack.network.api;

import com.pruebatecnica.leonardojpr_beetrack.network.response.NewsResponse;

import org.json.JSONObject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Leonardojpr on 06/05/18.
 */
public interface ArticleApi {

    @GET("v2/top-headlines")
    Observable<NewsResponse> getNews(@Query("country") String country,
                                     @Query("apiKey") String api_key);
}
