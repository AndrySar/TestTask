package com.bostongene;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andry on 30.05.17.
 */
public class YandexTranslate {

    private String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?" +
            "lang=en-ru&" +
            "key=trnsl.1.1.20170530T040630Z.3f98a6469ad85cb3.3269ae89f06a972f62ce20324f3c1a9cce3ba40e";

    private CloseableHttpClient client;
    private HttpPost httpPost;

    public YandexTranslate() throws Exception {

        // Resolved work with https protocols
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (certificate, authType) -> true).build();

        this.client = HttpClients.custom()
                .setSSLContext(sslContext)
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        this.httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
    }


    public String translate(String translateString) throws IOException {

        List<NameValuePair> dodyParameters = new ArrayList<NameValuePair>();

        // Formation body parameters
        dodyParameters.add(new BasicNameValuePair("text", translateString));

        // Set body parameters
        httpPost.setEntity(new UrlEncodedFormEntity(dodyParameters, HTTP.UTF_8));

        // Execute https request
        HttpResponse response = client.execute(httpPost);

        String result = null;

        // Check status code
        if( response.getStatusLine().getStatusCode() == 200 ) {
            result = getTextField(response.getEntity());
        }else {
            result = "Translation error";
        }
        return result;
    }

    /*
    * Parser body, get translate text
    * */
    private String getTextField(HttpEntity entity) throws IOException {
        JSONObject obj = new JSONObject(EntityUtils.toString(entity));
        String text = obj.get("text").toString();
        int start = text.indexOf("[");
        int end = text.indexOf("]");
        return text.substring(start + 2, end - 1);
    }
}
