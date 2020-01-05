package com.revolutionary.searchviewpagination;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements ActionMode.Callback {
    AdopterClass adaptor;
    SearchView searchView;
    private ArrayList<String> mainList;
    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    private LinearLayoutManager manager;
    ProgressBar progressBar;
    private ActionMode actionMode;
    private RecyclerItemClickListener touchListener;
    private ActionMode.Callback actionModeListener;
    private boolean isMultiSelect = false;
    private RecyclerView recyclerView;
    private ArrayList<String> selectedContacts;
    Toolbar toolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        casts();
        setRecyclerView();
        listTouchListener();
    }


    private void casts() {
        searchView = (SearchView) findViewById(R.id.searchView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        toolBar = (Toolbar) findViewById(R.id.toolBar);
        searchWork();
    }

    private void searchWork() {

        //searchViewBackgroundLine
        View v = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
        v.setBackgroundColor(Color.parseColor("white"));


        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                adaptor.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adaptor.getFilter().filter(query);
                return false;
            }
        });
    }


    private void setRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        manager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        mainList = new ArrayList<>();
        selectedContacts = new ArrayList<>();

        mainList.add("Adnan Ahmed " + mainList.size());
        mainList.add("Faizan Ahmed " + mainList.size());
        mainList.add("Imran Ahmed " + mainList.size());
        mainList.add("Faisal Ahmed " + mainList.size());
        mainList.add("Fahad Ahmed " + mainList.size());
        mainList.add("Rameez Ahmed " + mainList.size());
        mainList.add("Iftiqar Ahmed " + mainList.size());
        mainList.add("Bilal Ahmed " + mainList.size());
        mainList.add("Abrar Ahmed " + mainList.size());
        mainList.add("Imad Ahmed " + mainList.size());
        mainList.add("Asad Ahmed " + mainList.size());


        adaptor = new AdopterClass(mainList, MainActivity.this, selectedContacts);
        recyclerView.setAdapter(adaptor);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = recyclerView.getChildCount();
                totalItems = manager.getItemCount();
                scrollOutItems = manager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false;
                    getData();
                }
            }
        });


    }

    private void getData() {
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    mainList.add(Math.floor(Math.random() * 1000) + " New Item");
                    adaptor.notifyDataSetChanged();
                }
                progressBar.setVisibility(View.GONE);
            }
        }, 2000);

    }


    public void listTouchListener() {
        actionModeListener = this;
        touchListener = new RecyclerItemClickListener(MainActivity.this, recyclerView, actionMode, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (isMultiSelect)
                    multiSelect(position);

            }

            @Override
            public void onItemLongClick(View view, int position) {
                Log.d("", "");
                if (!isMultiSelect) {
                    isMultiSelect = true;
                    if (actionMode == null) {
                        actionMode = toolBar.startActionMode(actionModeListener); //show ActionMode.
                        multiSelect(position);
                    }
                }
            }
        });
        recyclerView.addOnItemTouchListener(touchListener);
    }


    @Override
    public void onDestroyActionMode(ActionMode mode) {
        isMultiSelect = false;
        selectedContacts.clear();
        actionMode = null;
        adaptor.notifyDataSetChanged();
    }


    private void multiSelect(int position) {
        if (actionMode != null) {

            RecyclerView.ViewHolder viewHolderForAdapterPosition = recyclerView.findViewHolderForAdapterPosition(position);

            if (selectedContacts.contains(mainList.get(position))) {
                selectedContacts.remove(mainList.get(position));
                adaptor.setMessageForeGround((FrameLayout) viewHolderForAdapterPosition.itemView.findViewById(R.id.itemView), false);
            } else {
                selectedContacts.add(mainList.get(position));
                adaptor.setMessageForeGround((FrameLayout) viewHolderForAdapterPosition.itemView.findViewById(R.id.itemView), true);
            }
        }


        if (selectedContacts.size() > 0) {
            actionMode.setTitle(String.valueOf(selectedContacts.size()) + " Selected"); //show selected item count on action mode.

        } else {
            selectedContacts.clear();
            actionMode.setTitle(""); //remove item count from action mode.
            actionMode.finish(); //hide action mode.
            actionMode = null;
        }
        if (actionMode != null) {
            actionMode.invalidate();
        }
    }


    @Override
    public void onDestroy() {
        if (actionMode != null) {
            actionMode.finish();
        }
        super.onDestroy();
    }


    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem menuItem) {

        return true;
    }

}






