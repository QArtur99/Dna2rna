package com.artf.dna;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements StringAdapter.OnListItemClickListener{

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    String rna;
    StringAdapter postsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        String dnaString = readFileAsString();
        rna = swapCharacters(dnaString);
        String rnaString = rnaBuilder();

        List<RnaObject> rnaList = new ArrayList<>();

        rnaList.add(new RnaObject("auggcu"));
        rnaList.add(new RnaObject("auggcc"));
        rnaList.add(new RnaObject("auggca"));
        rnaList.add(new RnaObject("auggcg"));

        for(RnaObject rnaResult : rnaList) {
            count(rnaString, rnaResult);
        }


        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        postsAdapter = new StringAdapter(rnaList, this);
        recyclerView.setAdapter(postsAdapter);

    }

    private String rnaBuilder() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; rna.length() > i; i++){
            char sing = rna.charAt(i);
            if(sing == 'g'){
                sb.append('c');
            }else if(sing == 'c'){
                sb.append('g');
            }else{
                sb.append(sing);
            }
        }
        return sb.toString();
    }

    public static int count(final String string, final RnaObject rnaObject){
        int count = 0;
        int idx = 0;
        List<Integer> position = new ArrayList<>();

        while ((idx = string.indexOf(rnaObject.getRnaValue(), idx)) != -1){
            position.add(idx);
            idx++;
            count++;
        }
        rnaObject.setPosition(position);
        rnaObject.setAmount(count);

        return count;
    }

    public String swapCharacters(String str) {
        str = str.replaceAll("[0-9]","");
        String[] escapeCharacters = { " ", "\n", "a", "t"};
        String[] onReadableCharacter = {"", "","u", "a"};
        for (int i = 0; i < escapeCharacters.length; i++) {
            str = str.replace(escapeCharacters[i], onReadableCharacter[i]);
        }
        return str;
    }

    public String readFileAsString() {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        FileInputStream input = null;
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;

        try {
            input = new FileInputStream(dir + "/bio.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(input);
            in = new BufferedReader(inputStreamReader);

            while ((line = in.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            in.close();
        } catch (IOException e) {
            //err
        }

        return stringBuilder.toString();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        RnaObject rnaObject = (RnaObject) postsAdapter.getDataAtPosition(clickedItemIndex);
        String rnaObjectString = new Gson().toJson(rnaObject);
        Intent intent = new Intent(this, PositionActivity.class);
        intent.putExtra("rnaObject", rnaObjectString);
        startActivity(intent);
    }
}
