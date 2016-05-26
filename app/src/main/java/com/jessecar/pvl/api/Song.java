package com.jessecar.pvl.api;

import java.io.Serializable;

public class Song implements Serializable {
    String id;
    String text;
    String artist;
    String title;
    String image_url;
    long created;
    int play_count;
    long last_played;
    int score;
    long sh_id;
    VoteURLs vote_urls;

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getImageURL() {
        return image_url;
    }

    public long getCreated() {
        return created;
    }

    public int getPlayCount() {
        return play_count;
    }

    public long getLastPlayed() {
        return last_played;
    }

    public int getScore() {
        return score;
    }

    public long getSh_id() {
        return sh_id;
    }

    public VoteURLs getVoteURLs() {
        return vote_urls;
    }
}

