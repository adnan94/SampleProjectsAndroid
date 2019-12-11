package com.tilismtech.demobindingadaptor;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tilismtech.demobindingadaptor.databinding.SingleItemBinding;

import java.util.List;

public class RecyclerAdaptor  extends RecyclerView.Adapter<personViewHolder> {
    List<String> list;
    public static Context context;
    LayoutInflater inflator;
    RecyclerBindingAdaptor recyclerBindingAdaptor;

    public RecyclerAdaptor(List<String> listMain, Context context,RecyclerBindingAdaptor bindingAdaptor) {
        this.list = listMain;
        this.context = context;
        inflator = LayoutInflater.from(context);
        recyclerBindingAdaptor = bindingAdaptor;
    }

//    @Override
//    public personViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View vv = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
//        final personViewHolder pv = new personViewHolder(vv);
//
//
//        vv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
//
//        return pv;
//    }

    @Override
    public personViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DataBindingUtil.setDefaultComponent(recyclerBindingAdaptor);
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SingleItemBinding itemBinding = SingleItemBinding.inflate(layoutInflater, parent, false);
        return new personViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(final personViewHolder holder, final int position) {
        holder.bind(list.get(position), this);
    }

    @Override
    public void onViewRecycled(personViewHolder holder) {
        super.onViewRecycled(holder);
        holder.recycle();
    }

//    @Override
//    public void onBindViewHolder(final personViewHolder holder, final int position) {
//        Glide.with(context).clear(holder.imageView);
//        Glide.with(this.context)
//                .load(list.get(position))
//                .into(holder.imageView);

//    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
