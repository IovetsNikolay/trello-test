package Junk;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trello.UI.core.BrowserFactory;
import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static com.trello.UI.core.Constants.LOGIN_DEFAULT;
import static com.trello.UI.core.Constants.PASSWORD_DEFAULT;

public class TrelloApiLogin extends BrowserFactory {
    Gson gson = new Gson();
    CookieStrorage cookieStrorage = new CookieStrorage();
    OkHttpClient client = new OkHttpClient.Builder().cookieJar(cookieStrorage).build();

    @Test
    public void testCookie() throws IOException {
        client.newCall(new Request.Builder().url("https://trello.com").build()).execute().body().string();
        for (Cookie cookie : cookieStrorage.cookies) {
            System.out.println(cookie);
        }
    }

    @Test
    public void LoginByApi() throws IOException, InterruptedException {
        FormBody formData = new FormBody.Builder()
                .add("method", LOGIN_DEFAULT)
                .add("factors[user]", PASSWORD_DEFAULT)
                .add("factors[password]", "")       //Type your value
                .build();
        Request request = new Request.Builder().url("Https://trello.com/1/authentification").post(formData).build();
        String response = client.newCall(request).execute().body().string();
        Map<String, String> map = gson.fromJson(response, new TypeToken<Map<String,String>>(){}.getType());
        String code = map.get("code");
        System.out.println("CODE: " + code);


        String dsc = cookieStrorage.cookies.stream().filter(cookie -> cookie.name().equals("dcs")).findFirst().get().value();
        FormBody sessionFormData = new FormBody.Builder()
                .add("authentification", code)
                .add("dcs", dsc)
                .build();
        Request requestSession = new Request.Builder().url("https://trello.com/1/authorization/session").post(sessionFormData).build();
        response = client.newCall(requestSession).execute().body().string();
        System.out.println(response);

        driver().get("https://trello.com");

        for (Cookie cookie : cookieStrorage.cookies) {
            org.openqa.selenium.Cookie seleniumCookie = new  org.openqa.selenium.Cookie(cookie.name(), cookie.value());
            driver().manage().addCookie(seleniumCookie);
        }
        driver().navigate().refresh();
        Thread.sleep(1000);
    }
}
