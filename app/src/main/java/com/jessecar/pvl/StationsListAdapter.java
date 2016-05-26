package com.jessecar.pvl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jessecar.pvl.api.NowPlayingStation;
import com.mikepenz.iconics.Iconics;
import com.squareup.picasso.Picasso;

import java.util.List;

class StationsListAdapter extends RecyclerView.Adapter<StationsListAdapter.StationViewHolder> {

    private List<NowPlayingStation> mStations;
    private Context mContext;

    void setStations(List<NowPlayingStation> stations) {
        mStations = stations;
        notifyDataSetChanged();
    }

    public NowPlayingStation getStation(int index) {
        return mStations.get(index);
    }

    @Override public StationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.station_row, parent, false);
        return new StationViewHolder(v);
    }

    @Override public void onBindViewHolder(StationViewHolder holder, int position) {
        NowPlayingStation station = mStations.get(position);
        holder.vStationName.setText(station.station.getName());
        holder.vStationDetails.setText(Iconics.style(mContext, new SpannableString(mContext.getResources().getString(R.string.station_details, station.station.getGenre(), station.streams.get(0).getListeners().getCurrent()))));
        String songName = "";
        if (station.streams.get(0).getCurrentSong() != null) {
            songName = station.streams.get(0).getCurrentSong().getText();
        }
        holder.vCurrentSong.setText(songName);
        Picasso.with(mContext).load(station.station.getImageURL()).into(holder.vStationImage);
    }

    @Override public int getItemCount() {
        if (mStations == null) return 0;
        return mStations.size();
    }

    static class StationViewHolder extends RecyclerView.ViewHolder {
        TextView vStationName;
        TextView vStationDetails;
        TextView vCurrentSong;
        ImageView vStationImage;

        StationViewHolder(View v) {
            super(v);
            vStationName = (TextView) v.findViewById(R.id.station_name);
            vStationDetails = (TextView) v.findViewById(R.id.station_details);
            vCurrentSong = (TextView) v.findViewById(R.id.station_current_song);
            vStationImage = (ImageView) v.findViewById(R.id.station_image);
        }
    }
}
