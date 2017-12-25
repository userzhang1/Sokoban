package com.example.administrator.sokoban;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnGameIntro =(Button) findViewById(R.id.btn_game_intro);

        btnGameIntro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(MainActivity.this,"你点击了游戏简介按钮",Toast.LENGTH_LONG).show();
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, Introduction2.class);
                startActivity(intent);
            }
        });
        Button btnStart_game =(Button) findViewById(R.id.btn_start_game);
        btnStart_game.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(MainActivity.this,"你点击了游戏按钮",Toast.LENGTH_LONG).show();
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,GameLevelActivity.class);
                startActivity(intent);
            }
        });
        Button btnExit_game =(Button)findViewById(R.id.btn_exit);
        btnExit_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("你点击了退出按钮");
                finish();
            }
        });


    }
}
