package com.tilismtech.customdurationviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;


/**
 * Created by Ali on 4/24/2017.
 */

public class Slider_Pager_Adaptor extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public Slider_Pager_Adaptor(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.slider_image_item, container, false);
        final ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
       String str = "https://i.imgur.com/tGbaZCY.jpg";
        if(position == 2){
             str = "http://i.imgur.com/DvpvklR.png";
        }
        Glide.with(context)
                .load(str)
                .into(imageView);
        imageView.setVisibility(View.VISIBLE);
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

}