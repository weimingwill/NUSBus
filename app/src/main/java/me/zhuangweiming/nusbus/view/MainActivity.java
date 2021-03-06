package me.zhuangweiming.nusbus.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import me.zhuangweiming.nusbus.AboutPageActivity;
import me.zhuangweiming.nusbus.R;
import me.zhuangweiming.nusbus.model.BusStop;
import me.zhuangweiming.nusbus.model.Tab;
import me.zhuangweiming.nusbus.view.fragmentcallbacks.BusStopClickCallback;


public class MainActivity extends AppCompatActivity implements BusStopClickCallback{
    private Toolbar mToolbar;
    private TabLayout tabLayout;
    private ViewPager tabViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        tabViewPager = (ViewPager) findViewById(R.id.viewpager);
        Tab.setupMainActivityViewPager(tabViewPager, getSupportFragmentManager());

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(tabViewPager);
        Tab.setupTabIcons(tabLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, AboutPageActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBusStopClicked(BusStop stop) {
        Intent intent = ShuttleActivity.getIntent(this, stop);
        startActivity(intent);
    }
}

