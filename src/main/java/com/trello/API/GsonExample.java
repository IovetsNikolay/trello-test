package com.trello.API;

import com.google.gson.Gson;
import com.trello.API.Models.Card;
import org.testng.annotations.Test;

public class GsonExample {
    String cardJson = " {\n" +
            "        \"id\": \"5a8aaf5b8bc73165a15f071f\",\n" +
            "        \"desc\": \"Some CardTemp Description\",\n" +
            "        \"id\": \"5a8aaf518e5ac8fff8dd5f67\",\n" +
            "        \"idList\": \"5a8aaf5838440fc96023077d\",\n" +
            "        \"name\": \"Jack\",\n" +
            "        \"url\": \"https://trello.com/c/Q48O9oWn/1-jack\"\n" +
            "    }\n";

    @Test
    public void javaToJson(){
        Gson gson = new Gson();
        Card cardTemp = new Card();
        cardTemp.name = "New Card1";
        cardTemp.desc = "SOME DESCRIPTION";
        String json = gson.toJson(cardTemp);
        System.out.println(json);
    }

    @Test
    public void jsonToJava(){
        Gson gson = new Gson();
        Card cardTemp = gson.fromJson(cardJson, Card.class);
        System.out.println(cardTemp);
        System.out.println(cardTemp.name);
        System.out.println(cardTemp);
    }
}
