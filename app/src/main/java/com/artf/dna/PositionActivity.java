package com.artf.dna;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ART_F on 2018-01-08.
 */

public class PositionActivity extends AppCompatActivity implements PositionAdapter.OnListItemClickListener {
    RnaObject rnaObject;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Intent intent = getIntent();
        rnaObject = new Gson().fromJson(intent.getStringExtra("rnaObject"), new TypeToken<RnaObject>() {}.getType());

        getSupportActionBar().setTitle(rnaObject.getRnaValue().toUpperCase());

        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        PositionAdapter postsAdapter = new PositionAdapter(rnaObject.getPosition(), this);
        recyclerView.setAdapter(postsAdapter);

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }
}
