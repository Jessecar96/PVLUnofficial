package com.jessecar.pvl.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class RadioStation implements Serializable {
    public ArrayList<RadioStationStream> streams;
    private int id;
    private String name;
    private String shortcode;
    private String genre;
    private String category;
    private String affiliation;
    private String type;
    private String image_url;
    private String web_url;
    private String stream_url;
    private String twitter_url;
    private String irc;
    private int sort_order;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortcode() {
        return shortcode;
    }

    public String getGenre() {
        return genre;
    }

    public String getCategory() {
        return category;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getType() {
        return type;
    }

    public String getImageURL() {
        return image_url;
    }

    public String getWebURL() {
        return web_url;
    }

    public String getStreamURL() {
        return stream_url;
    }

    public String getTwitterURL() {
        return twitter_url;
    }

    public String getIrc() {
        return irc;
    }

    public int getSortOrder() {
        return sort_order;
    }
}
