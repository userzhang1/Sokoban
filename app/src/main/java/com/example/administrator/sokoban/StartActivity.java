package com.example.administrator.sokoban;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Timer timer= new Timer();//创建Timer对象，用于设置启动界面显示的时间

        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                //从启动界面跳转到游戏主界面的
                startActivity(new Intent(StartActivity.this,MainActivity.class));
                finish();
            }
        };
        timer.schedule(timerTask,2000);
    }
}
