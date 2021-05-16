package com.example.wroclawtourgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.AssetManager;
import android.os.Bundle;

import com.example.wroclawtourgame.logic.TourReader;
import com.example.wroclawtourgame.model.Tour;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToursListActivity extends AppCompatActivity {

    private List<Tour> mTours;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ToursListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadTours();

        mRecyclerView = findViewById(R.id.toursRecyclerView);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ToursListAdapter(mTours, this);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void loadTours() {
        mTours = new ArrayList<>();

        AssetManager assetManager = this.getAssets();
        TourReader tourReader = new TourReader();
        Tour tour;
        try {
            String[] tours = assetManager.list("tours");

            for (String tourFileName : tours) {
                tour = tourReader.parse(assetManager.open("tours/" + tourFileName));
                mTours.add(tour);
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}