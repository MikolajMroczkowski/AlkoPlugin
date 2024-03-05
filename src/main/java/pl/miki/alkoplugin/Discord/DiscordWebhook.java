package pl.miki.alkoplugin.Discord;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.simple.JSONObject;

import java.io.IOException;

public class DiscordWebhook {
    String url;
    public DiscordWebhook(String url){
        this.url = url;
    }
    public void sendMessage(String nickname,String profileIMG,String message) {
        OkHttpClient client = new OkHttpClient();

        JSONObject json = new JSONObject();
        json.put("content", message);
        json.put("username", nickname);
        json.put("avatar_url", profileIMG);

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
