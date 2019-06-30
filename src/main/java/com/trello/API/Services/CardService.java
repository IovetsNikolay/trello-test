package com.trello.API.Services;

import com.trello.API.Models.Attachment;
import com.trello.API.Models.Card;
import com.trello.API.Models.CheckList;
import com.trello.API.Models.Members;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface CardService {

    @GET("lists/{id}/cards")
    Call<List<Card>> getCards(@Path("id") String id);

    @GET("cards/{id}")
    Call<Card> getCard(@Path("id") String id);

    @POST("cards")
    Call<Card> createCard(@Query("name") String name, @Query("idList") String idList);

    @PUT("cards/{id}")
    Call<Card> updateCard(@Path("id") String id, @Body Card card);

    @DELETE("cards/{id}")
    Call<ResponseBody> deleteCard(@Path("id") String id);

    @GET("cards/{id}/members")
    Call<List<Members>> getMembersList(@Path("id") String id);

    @GET("cards/{id}/checklists")
    Call<List<CheckList>> getChecklistList(@Path("id") String id);

    @GET("cards/{id}/attachments")
    Call<List<Attachment>> getAttachmentsList(@Path("id") String id);
}
