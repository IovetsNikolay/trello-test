package com.trello.API.Services;

import com.trello.API.Models.Board;
import com.trello.API.Models.Token;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface BoardsService {

    @Multipart
    @POST("authentication")
    Call<ResponseBody> getAuthentificationResponse(@Part ("method") String actionId, @Path("factors[user]") String user, @Path("factors[password]") String password, @Path("castleClientId") String castleClientId);

    @GET("members/{id}/tokens")
    Call<List<Token>> getUserTokenList(@Path("id") String id);

    @GET("members/{id}/boards")
    Call<List<Board>> getMembersBoards(@Path("id") String id);

    @POST("boards")
    Call<Board> createBoard(@Query("name") String name);

    @DELETE("boards/{id}")
    Call<ResponseBody> deleteBoard(@Path("id") String id);

    @PUT("boards/{id}")
    Call<Board> updateBoard(@Path("id") String id, @Body Board board);

}
