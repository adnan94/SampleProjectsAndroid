package com.revolutionary.searchviewpagination;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Ali on 5/3/2017.
 */

public class AdopterClass extends RecyclerView.Adapter<AdopterClass.personViewHolder> implements Filterable {
    ArrayList<String> list;
    private ArrayList<String> listFiltered;
    public static Context context;
    LayoutInflater inflator;
    ArrayList<String> selectedContacts;

    public AdopterClass(ArrayList<String> listMain, Context context,ArrayList<String> selectedContacts) {
        this.listFiltered = listMain;
        this.selectedContacts = selectedContacts;
        this.list = listMain;
        this.context = context;
        inflator = LayoutInflater.from(context);
    }

    @Override
    public personViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vv = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
        final personViewHolder pv = new personViewHolder(vv);
        vv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return pv;
    }

    @Override
    public void onBindViewHolder(final personViewHolder holder, final int position) {
        holder.item.setText(listFiltered.get(position));
        if(!selectedContacts.isEmpty() && selectedContacts.contains(list.get(position))) {
            holder.itemLayout.setForeground(new ColorDrawable(ContextCompat.getColor(holder.itemLayout.getContext(), R.color.activated)));
        }else{
            holder.itemLayout.setForeground(new ColorDrawable(ContextCompat.getColor(holder.itemLayout.getContext(), R.color.trans)));
        }

    }


    @Override
    public int getItemCount() {
        return listFiltered.size();
    }



    public void setMessageForeGround(FrameLayout view, boolean b) {
        if(b) {
            view.setForeground(new ColorDrawable(ContextCompat.getColor(view.getContext(), R.color.activated)));
        }else{
            view.setForeground(new ColorDrawable(ContextCompat.getColor(view.getContext(), R.color.trans)));
        }
    }

    public static class personViewHolder extends RecyclerView.ViewHolder {

        TextView item;
        FrameLayout itemLayout;

        public personViewHolder(View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.item);
            itemLayout = (FrameLayout) itemView.findViewById(R.id.itemView);

        }
    }










    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listFiltered = list;
                } else {
                    ArrayList<String> filteredList = new ArrayList<>();
                    for (String row : listFiltered) {
                        if (row.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    listFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if (filterResults.values != null) {
                    listFiltered = (ArrayList<String>) filterResults.values;
                    notifyDataSetChanged();
                }
            }
        };
    }




}
