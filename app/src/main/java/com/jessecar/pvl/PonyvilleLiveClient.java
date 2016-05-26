package com.jessecar.pvl;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jessecar.pvl.api.NowPlayingList;
import com.jessecar.pvl.api.NowPlayingResponse;
import com.jessecar.pvl.api.NowPlayingResponseDeserializer;
import com.jessecar.pvl.api.RadioStream;
import com.jessecar.pvl.api.RadioStreamDeserializer;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;

public class PonyvilleLiveClient {
    private static final String BASE_URL = "http://ponyvillelive.com/api";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void getNowPlaying(final StationListResponse callback) {
        PonyvilleLiveClient.get("/nowplaying", null, new AsyncHttpResponseHandler() {
            @Override public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseString = PonyvilleLiveClient.byteToString(responseBody);
                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapter(NowPlayingList.class, new NowPlayingResponseDeserializer());
                builder.registerTypeAdapter(RadioStream.class, new RadioStreamDeserializer());
                Gson gson = builder.setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES).create();
                NowPlayingResponse response = gson.fromJson(responseString, NowPlayingResponse.class);
                callback.onResult(response);
            }

            @Override public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String responseString = PonyvilleLiveClient.byteToString(responseBody);
                Log.d("PVL", String.format("statusCode: %s body: %s", statusCode, responseString));
                callback.onResult(null);
            }
        });
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static String byteToString(byte[] bytes) {
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    interface StationListResponse {
        void onResult(NowPlayingResponse result);
    }
}
