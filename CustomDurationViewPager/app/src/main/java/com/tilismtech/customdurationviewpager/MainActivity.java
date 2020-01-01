package com.tilismtech.customdurationviewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Supplier;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity {

    private ViewPagerCustomDuration viewPager;
    private Slider_Pager_Adaptor slider_pager_adaptor;
    private ArrayList durationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPagerCustomDuration) findViewById(R.id.viewPager);
        viewPager.setScrollDuration(2000);
        getSliderData();

    }


    private void getSliderData() {
        durationList = new ArrayList();

        //set list
        durationList.add(5);
        durationList.add(5);
        durationList.add(20);
        durationList.add(5);

        slider_pager_adaptor = new Slider_Pager_Adaptor(MainActivity.this);
        viewPager.setAdapter(slider_pager_adaptor = new Slider_Pager_Adaptor(MainActivity.this));
        slider_pager_adaptor.notifyDataSetChanged();
        viewPager.setOffscreenPageLimit(durationList.size());
        pageSwitcher(durationList,(int)durationList.get(0), durationList.size());
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("",""+position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("", "");
                timer.stop();
                timer = null;
                pageSwitcher(durationList,(int)durationList.get(position),durationList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("", "");
            }
        });
    }


    EasyTimer timer;
    int page = 0;

    public void pageSwitcher(ArrayList<Integer> list, long delay, final int pages) {
        timer = new EasyTimer(delay * 1000);// At this line a new Thread will be created
        timer.setOnTaskRunListener(new EasyTimer.OnTaskRunListener() {
        @Override
        public void onTaskRun(long past_time, String rendered_time) {
        if (page > pages) {
            page = 0;
        }
        viewPager.setCurrentItem(page++);
    }
});
        timer.start();
    }
}
