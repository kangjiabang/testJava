package http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2018/12/3.
 */
public class HttpConnectionTest {

    OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {
            new HttpConnectionTest().run("http://183.232.231.173");
    }


    /**
     * @param url
     * @return
     * @throws IOException
     */
    public void run(String url)  {
        InputStream in = null;
        URLConnection httpConnection = null;
        try {
            URL urlOri = new URL(url);
            httpConnection = urlOri.openConnection();
            httpConnection.setRequestProperty("Connection", "close");

            //获取读入流
            in = httpConnection.getInputStream();
            //放入缓存流
            InputStream raw = new BufferedInputStream(in);
            //最后使用Reader接收
            Reader r = new InputStreamReader(raw);

            //打印输出
            int c;
            while ((c = r.read()) > 0) {
                System.out.print((char) c);
            }
            try {
                Thread.sleep(30*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {

        } finally {
            if (in != null) {
                /*try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }*/
            }
        }
    }

}
