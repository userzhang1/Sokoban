package com.example.administrator.sokoban;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    public static final String KEY_SELECTED_LEVEL = "Selected_Level";
//    public Button prv_btn;
    private GameState mCurrentState;
//    String[] levelList =new String[]{"第1关","第2关","第3关","第4关"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_game);

        int selected_level =getIntent().getIntExtra(KEY_SELECTED_LEVEL,1);

        mCurrentState =new GameState(GameLevels.getLevel(selected_level));
//        TextView tvLevel = (TextView) findViewById(R.id.tv_select_level1);
//        tvLevel.setText(getResources().getString(R.string.what_you_select)+"第"+selected_level+"关");
//

        GameView gameView =new GameView(this);
        setContentView(gameView);


    }
    public GameState getCurrentState(){
        return mCurrentState;
    }
}
