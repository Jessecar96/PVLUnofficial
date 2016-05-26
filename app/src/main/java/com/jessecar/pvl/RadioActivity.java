package com.jessecar.pvl;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jessecar.pvl.api.NowPlayingStation;
import com.jessecar.pvl.api.RadioStation;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RadioActivity extends AppCompatActivity {

    // Views
    @BindView(R.id.toolbar) Toolbar vToolbar;
    @BindView(R.id.station_name_rdr) TextView station_name;
    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_player);
        ButterKnife.bind(this);

        NowPlayingStation station = (NowPlayingStation) getIntent().getSerializableExtra("station");
        station_name.setText(station.station.getName());

        setSupportActionBar(vToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setTitle(station.station.getName());
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);
        vToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                finish();
            }
        });
    }
}
