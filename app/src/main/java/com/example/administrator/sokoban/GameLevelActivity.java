package com.example.administrator.sokoban;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.List;

public class GameLevelActivity extends AppCompatActivity {
//    String[] levelList =new String[]{"第1关","第2关","第3关","第4关"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_level);
        GridView gv_level =(GridView) findViewById(R.id.gv_levels);
//        ArrayAdapter<String> arrayAdapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,levelList);
        ArrayAdapter<String> arrayAdapter =new ArrayAdapter<String>(this,R.layout.gv_levels_item_textview,GameLevels.getLevelList());
        gv_level.setAdapter(arrayAdapter);
        gv_level.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adpterView, View view, int i,long l){
                Intent intent = new Intent(GameLevelActivity.this,GameActivity.class);
                intent.putExtra(GameActivity.KEY_SELECTED_LEVEL, i + 1);
                startActivity(intent);
            }
        });

    }

}
