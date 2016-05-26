package com.jessecar.pvl.api;

import java.io.Serializable;
import java.util.List;

public class RadioStream implements Serializable {

    int id;
    String name;
    String url;
    String type;
    boolean is_default;
    String status;
    int bitrate;
    String format;
    RadioListeners listeners;
    Song current_song;
    List<SongHistoryItem> song_history;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getIsDefault() {
        return is_default;
    }

    public void setIsDefault(boolean is_default) {
        this.is_default = is_default;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public RadioListeners getListeners() {
        return listeners;
    }

    public void setListeners(RadioListeners listeners) {
        this.listeners = listeners;
    }

    public Song getCurrentSong() {
        return current_song;
    }

    public void setCurrentSong(Song current_song) {
        this.current_song = current_song;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSongHistory(List<SongHistoryItem> song_history) {
        this.song_history = song_history;
    }
}

