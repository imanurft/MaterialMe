package com.example.ammar.materialme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {
    TextView sportsTitle;
    TextView sportsSubtitle;
    TextView sportsDescription;
    ImageView sportsImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        sportsImage = (ImageView) findViewById(R.id.sportsImageDetail);
        sportsTitle = (TextView) findViewById(R.id.titleDetail);
        sportsSubtitle = (TextView) findViewById(R.id.subTitleDetail);
        sportsDescription = (TextView) findViewById(R.id.descriptionDetail);

        Intent intent = getIntent();
        if (intent.hasExtra(SportAdapter.EXTRA_SPORT_TITLE)){
            String title = intent.getStringExtra(SportAdapter.EXTRA_SPORT_TITLE);
            sportsTitle.setText(title);
        }
        if (intent.hasExtra(SportAdapter.EXTRA_SPORT_SUB_TITLE)){
            String subtitle = intent.getStringExtra(SportAdapter.EXTRA_SPORT_SUB_TITLE);
            sportsSubtitle.setText(subtitle);
        }
        if (intent.hasExtra(SportAdapter.EXTRA_SPORT_IMAGE_RESOURCE)) {
            int imageResource = intent.getIntExtra(SportAdapter.EXTRA_SPORT_IMAGE_RESOURCE, 0);
            Glide.with(this).load(Integer.valueOf(imageResource)).into(sportsImage);
        }

        if (intent.hasExtra(SportAdapter.EXTRA_SPORT_DESCRIPTION)) {
            String description = intent.getStringExtra(SportAdapter.EXTRA_SPORT_DESCRIPTION);
            sportsDescription.setText(description);
        }
    }
}
