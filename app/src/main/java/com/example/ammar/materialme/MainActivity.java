package com.example.ammar.materialme;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<Sport> mSportsData;
    private SportAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSportsData = new ArrayList<>();

        mAdapter = new SportAdapter(this, mSportsData);
        mRecyclerView.setAdapter(mAdapter);
        initializeData();

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.
                SimpleCallback(
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN |
                        ItemTouchHelper.UP,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(mSportsData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                remove data from data set
                mSportsData.remove(viewHolder.getAdapterPosition());
//                notify adapter
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

//        attach recyclerView to itemTouchHelper
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void initializeData() {
        String[] sportsList = getResources().getStringArray(R.array.sports_titles);
        String[] sportsInfo = getResources().getStringArray(R.array.sports_info);
        String[] sportsDescription = getResources().getStringArray(R.array.sports_description);
//        int[] sportsImage = getResources().getIntArray(R.array.sports_images);
        TypedArray sportsImageResources = getResources().obtainTypedArray(R.array.sports_images);

        for (int i = 0; i < sportsInfo.length; i++) {
            mSportsData.add(new Sport(sportsList[i], sportsInfo[i],
                    sportsImageResources.getResourceId(i, 0), sportsDescription[i]));
        }

        sportsImageResources.recycle();

        mAdapter.notifyDataSetChanged();
    }

    public void resetSport(View view) {
        mSportsData.clear();
        initializeData();
    }
}
