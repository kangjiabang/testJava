package http;

import okhttp3.*;

import java.io.IOException;

/**
 * Created by Administrator on 2018/12/3.
 */
public class HttpTest {

    OkHttpClient client = new OkHttpClient();
    public static void main(String[] args) {
        try {
            System.out.println(new HttpTest().runAsync("http://183.232.231.173"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param url
     * @return
     * @throws IOException
     */
    public String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     *
     * @param url
     * @return
     * @throws IOException
     */
    public String runAsync(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

       client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("success");

            }
        });
        return "over";
    }
}
