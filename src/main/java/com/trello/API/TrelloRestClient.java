package com.trello.API;

import com.trello.API.Interseptors.TrelloAuthInterceptor;
import com.trello.API.Services.BoardsService;
import com.trello.API.Services.CardService;
import com.trello.API.Services.LabelsService;
import com.trello.API.Services.ListsService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class TrelloRestClient {

    public static final String HOME_TO_BASE_URL = "https://api.trello.com/1/";

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(new TrelloAuthInterceptor())
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .client(client)
            .baseUrl(HOME_TO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public BoardsService boardsService = retrofit.create(BoardsService.class);
    public ListsService listsService = retrofit.create(ListsService .class);
    public CardService cardService = retrofit.create(CardService.class);
    public LabelsService labelsService = retrofit.create(LabelsService.class);
}
