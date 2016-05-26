package com.jessecar.pvl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    // Views
    @BindView(R.id.toolbar) Toolbar vToolbar;
    @BindView(R.id.mainFrame) FrameLayout vMainFrame;

    private static String TAG = "PVL";
    private ActionBar mActionBar;
    private Drawer mDrawer;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;

        setSupportActionBar(vToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setTitle(R.string.brand);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(vToolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.radio_stations).withTag("radio").withIcon(GoogleMaterial.Icon.gmd_radio),
                        new PrimaryDrawerItem().withName(R.string.shows).withTag("shows").withIcon(GoogleMaterial.Icon.gmd_tv),
                        new PrimaryDrawerItem().withName(R.string.conventions).withTag("conventions").withIcon(GoogleMaterial.Icon.gmd_group),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.pvl_web).withTag("web").withIcon(GoogleMaterial.Icon.gmd_web),
                        new PrimaryDrawerItem().withName(R.string.about).withTag("about").withIcon(GoogleMaterial.Icon.gmd_info)
                ).withOnDrawerItemClickListener(new OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        String tag = drawerItem.getTag().toString();
                        Log.d(TAG, "Fragment tag: " + tag);

                        Fragment newFragment = null;
                        switch (tag) {
                            case "radio":
                                newFragment = new StationsFragment();
                                break;

                            case "web":
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ponyvillelive.com"));
                                startActivity(browserIntent);
                                break;

                            case "about":
                                startActivity(new Intent(mContext, AboutActivity.class));
                                break;
                        }

                        if (newFragment != null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.mainFrame, newFragment)
                                    .commit();
                        }

                        mDrawer.closeDrawer();
                        return true;
                    }
                }).build();

        // Launch main fragment
        Fragment newFragment = new StationsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainFrame, newFragment)
                .commit();

    }
}
