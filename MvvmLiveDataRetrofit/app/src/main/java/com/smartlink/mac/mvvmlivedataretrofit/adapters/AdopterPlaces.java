package com.smartlink.mac.mvvmlivedataretrofit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.smartlink.mac.mvvmlivedataretrofit.model.PlacesModel;
import com.smartlink.mac.mvvmlivedataretrofit.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdopterPlaces extends RecyclerView.Adapter<AdopterPlaces.personViewHolder> {
    List<PlacesModel> list;
    public static Context context;
    LayoutInflater inflator;

    public AdopterPlaces(List<PlacesModel> listMain, Context context) {
        this.list = listMain;
        this.context = context;
        inflator = LayoutInflater.from(context);
    }

    @Override
    public AdopterPlaces.personViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vv = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_feeds, parent, false);
        final personViewHolder pv = new personViewHolder(vv);


        vv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return pv;
    }

    @Override
    public void onBindViewHolder(final AdopterPlaces.personViewHolder holder, final int position) {
        holder.name.setText(list.get(position).getName());
        Glide.with(context).clear(holder.imageView);
        Glide.with(this.context)
                .load(list.get(position).getPicUrl())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class personViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        RelativeLayout contailner;
        CircleImageView imageView;

        public personViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            imageView = (CircleImageView) itemView.findViewById(R.id.iv);

        }
    }


}
