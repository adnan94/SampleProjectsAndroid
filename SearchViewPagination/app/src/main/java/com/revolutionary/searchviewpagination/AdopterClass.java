package com.revolutionary.searchviewpagination;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
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

    public AdopterClass(ArrayList<String> listMain, Context context) {
        this.listFiltered = listMain;
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


    }


    @Override
    public int getItemCount() {
        return listFiltered.size();
    }

    public static class personViewHolder extends RecyclerView.ViewHolder {

        TextView item;

        public personViewHolder(View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.item);

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
