package com.example.administrator.sokoban;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/3.
 */

public class GameLevels {
    public static final int DEFAULT_ROW_NUM = 12;
    public static final int DEFAULT_COLUMN_NUM = 12;

    //游戏区单元格放了什么？
    public static final char NOTHING =' ';//该单元格表示啥也没有
    public static final char BOX ='B';//g该单元格放的是箱子
    public static final char FLAG='F';//红旗，表示箱子的目的的
    public static final char MAN ='M';//搬运工
    public static final char WALL = 'W';//樯
    public static final char MAN_FALG = 'R';//搬运工+红旗
    public static final char BOX_FALG = 'X';//箱子加红旗



    public static final String[] LEVEL_1={
            "WWWWWWWWWWWW",
            "W         FW",
            "W          W",
            "W          W",
            "W   WWWW   W",
            "W          W",
            "W    B     W",
            "W    M     W",
            "W          W",
            "W          W",
            "W          W",
            "WWWWWWWWWWWW"
    };

    public static final String[] LEVEL_2={
            "WWWWWWWWWWWW",
            "W          W",
            "W          W",
            "W  WWWWWWW W",
            "W  W FFB W W",
            "W  W W B W W",
            "W  W W W W W",
            "W  W BMW W W",
            "W  WFB   W W",
            "W  WFWWWW  W",
            "W  WW      W",
            "WWWWWWWWWWWW"
    };
    public static final String[] LEVEL_3={
            "WWWWWWWWWWWW",
            "W        F W",
            "W          W",
            "W          W",
            "W   WWWW   W",
            "W    M     W",
            "W    B     W",
            "W     B  F W",
            "W          W",
            "W          W",
            "W          W",
            "WWWWWWWWWWWW"
    };


    public static ArrayList<String[]> OriginalLevels = new ArrayList<>();//存储多个开局的列表
    //loadGameLevels()的作用是加载关卡列表
   public static void loadGameLevels(){
       if(OriginalLevels.isEmpty()){
           OriginalLevels.add(LEVEL_1);//把第一关的开局添加到开局列表中
           OriginalLevels.add(LEVEL_2);
           OriginalLevels.add(LEVEL_3);

       }
   }
   //getLevel()是根据关卡号level得到该关卡的开局（用String【】实现的矩阵）
    public static String[] getLevel(int level){//level参数是关卡号
        loadGameLevels();
        return OriginalLevels.get(level-1);
    }
    public static List<String> getLevelList(){
        loadGameLevels();//加载关卡列表
        List<String> levelList = new ArrayList<>();//创建关卡名列表对象levelList ,并分配存储空间
        int levelNum = OriginalLevels.size();//得到关卡数目
        for(int i = 1;i<=levelNum;i++){    //对 每一关i
            levelList.add(new String("第"+i+"关"));//把关卡名
        }
        return levelList;

    }

}
