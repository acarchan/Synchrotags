package fr.voltanite.activity;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.json.JSONObject;

public class HTTPPoster {
    public static HttpResponse doPost(String url, JSONObject c) throws ClientProtocolException, IOException 
    {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost request = new HttpPost(url);
        HttpEntity entity;
        StringEntity s = new StringEntity(c.toString());
        //multipart for PHP servers
       // SimpleMultipartEntity s = new SimpleMultipartEntity();
       // s.addPart("form", c.toString());
        s.setContentEncoding(new BasicHeader("UTF_8", "application/json"));
        entity = s;
        request.setEntity(entity);
        HttpResponse response;
        response = httpclient.execute(request);
        return response;
    }
}
