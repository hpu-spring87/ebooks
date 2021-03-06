/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015 HackerEarth
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:*
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.*
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.chunsheng.ebooks.hackerearth.api.requests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import me.chunsheng.ebooks.hackerearth.api.options.BaseOptions;
import me.chunsheng.ebooks.hackerearth.api.responses.BaseResponse;

public class BaseRequest {

    public final BaseOptions options;
    protected final String clientSecret;
    public final String USER_AGENT = "Mozilla/5.0";

    public BaseRequest(String clientSecret, BaseOptions options) {
        this.clientSecret = clientSecret;
        this.options = options;
    }

    public BaseResponse Execute() {
        return null;
    }

    protected String sendRequest(final String endpoint, final List<NameValuePair> options) {
        try {
            HttpPost httpPost = new HttpPost(endpoint);
            httpPost.setEntity(new UrlEncodedFormEntity(options));
            httpPost.setHeader(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF_8");
            //httpPost.setHeader("Content-type", "application/json");
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(httpPost);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer resultBuffer = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                resultBuffer.append(line);
            }
            return resultBuffer.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Blocking IO.
    public BaseResponse waitForResult() {
        return null;
    }

}
