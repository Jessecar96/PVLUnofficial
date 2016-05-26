package com.jessecar.pvl;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jessecar.pvl.api.NowPlayingResponse;
import com.jessecar.pvl.api.NowPlayingStation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StationsFragment extends Fragment {

    // Views
    @BindView(R.id.stations_list) RecyclerView vStationsList;

    private Context mContext;
    private StationsListAdapter mAdapter;

    public StationsFragment() {

    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        this.mContext = getContext();
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stations, container, false);
        ButterKnife.bind(this, view);

        vStationsList.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        vStationsList.setLayoutManager(mLayoutManager);
        mAdapter = new StationsListAdapter();
        vStationsList.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.divider));
        vStationsList.setAdapter(mAdapter);

        ItemClickSupport.addTo(vStationsList).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent playerIntent = new Intent(mContext, RadioActivity.class);
                playerIntent.putExtra("station", mAdapter.getStation(position));
                startActivity(playerIntent);
            }
        });

        loadStationsList();
        return view;
    }

    private void loadStationsList() {
        PonyvilleLiveClient.getNowPlaying(new PonyvilleLiveClient.StationListResponse() {
            @Override public void onResult(NowPlayingResponse result) {
                if (result != null) {
                    List<NowPlayingStation> stations = new ArrayList<>();
                    for (int i = 0; i < result.result.stations.size(); i++) {
                        NowPlayingStation station = result.result.stations.get(i);
                        if (station.status.equals("online")) {
                            stations.add(station);
                        }
                    }
                    mAdapter.setStations(stations);
                } else {
                    Toast.makeText(mContext, R.string.stations_failed, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
