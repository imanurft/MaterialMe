package com.example.ammar.materialme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ammar on 8/28/2017.
 */

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.ViewHolder> {
    public static final String EXTRA_SPORT_TITLE = "title";
    public static final String EXTRA_SPORT_IMAGE_RESOURCE = "image_resource";
    public static final String EXTRA_SPORT_SUB_TITLE = "subtitle";
    public static final String EXTRA_SPORT_DESCRIPTION = "description";

    Context mContext;
    ArrayList<Sport> mSportsData;

    public SportAdapter(Context mContext, ArrayList<Sport> mSportsData) {
        this.mContext = mContext;
        this.mSportsData = mSportsData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Sport sport = mSportsData.get(position);
        holder.mTitleText.setText(sport.getTitle());
        holder.mInfoText.setText(sport.getInfo());
        Glide.with(mContext).load(sport.getImageResource()).into(holder.mSportsImage);
        /*
        //can also be used to populate data
        holder.bindTo(currentSport);
        */
    }

    @Override
    public int getItemCount() {
        return mSportsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mSportsImage;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleText = (TextView) itemView.findViewById(R.id.title);
            mInfoText = (TextView) itemView.findViewById(R.id.subTitle);
            mSportsImage = (ImageView) itemView.findViewById(R.id.sportsImage);
            itemView.setOnClickListener(this);
        }

        void bindTo(Sport currentSport) {
            //Populate the textviews with data
            mTitleText.setText(currentSport.getTitle());
            mInfoText.setText(currentSport.getInfo());

        }

        @Override
        public void onClick(View v) {
            Sport currentSport = mSportsData.get(getAdapterPosition());
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra(EXTRA_SPORT_TITLE, currentSport.getTitle());
            intent.putExtra(EXTRA_SPORT_IMAGE_RESOURCE, currentSport.getImageResource());
            intent.putExtra(EXTRA_SPORT_SUB_TITLE, currentSport.getInfo());
            intent.putExtra(EXTRA_SPORT_DESCRIPTION, currentSport.getDescription());
            mContext.startActivity(intent);
        }
    }
}
