package com.trello.API.Services;

import com.trello.API.Models.ListOnBoard;
import retrofit2.Call;
import retrofit2.http.*;


import java.util.List;

public interface ListsService {

    @GET("boards/{id}/lists")
    Call<List<ListOnBoard>> getLists(@Path("id") String id);

    @POST("lists")
    Call<ListOnBoard> createList(@Query("name") String name, @Query("idBoard") String idBoard);

    @PUT("lists/{id}")
    Call<ListOnBoard> updateList(@Path("id") String id, @Body ListOnBoard listOnBoard);

    @PUT("lists/{id}/closed")
    Call<ListOnBoard> archiveBoard(@Path("id") String id, @Query("value") Boolean closeValue, @Body ListOnBoard listOnBoard);
}
