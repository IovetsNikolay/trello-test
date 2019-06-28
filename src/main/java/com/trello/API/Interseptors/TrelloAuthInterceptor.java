package com.trello.API.Interseptors;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


import java.io.IOException;

public class TrelloAuthInterceptor implements Interceptor{
    private static final String KEY = "854673677fcfa7ebe096e48ba019982a";
    private static final String TOKEN = "eb0a688d69badfd17c66087857003fe4ff63667bdbcd012d4dd1d8bcdb124854";

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("key", KEY)
                .addQueryParameter("token", TOKEN)
                .build();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
