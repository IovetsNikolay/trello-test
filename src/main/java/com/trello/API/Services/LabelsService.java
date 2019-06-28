package com.trello.API.Services;

import com.trello.API.Models.CardLabel;
import com.trello.API.Models.ColoursEnum;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;

public interface LabelsService {

    @GET("cards/{id}/stickers")
    Call<List<CardLabel>> getLabels(@Path("id") String id);

    @POST("labels")
    Call<CardLabel> createLabel(@Query("name") String name, @Query("color") ColoursEnum coloursEnum, @Query("idBoard") String idBoard);

    @PUT("labels/{id}")
    Call<CardLabel> modifyLabel(@Path("id") String id, @Body CardLabel cardLabel);

    @DELETE("labels/{id}")
    Call<ResponseBody> deleteLabel(@Path("id") String id);

}
