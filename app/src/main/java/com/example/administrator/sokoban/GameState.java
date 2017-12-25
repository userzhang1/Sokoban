package com.example.administrator.sokoban;

/**
 * Created by Administrator on 2017/12/3.
 */

public class GameState {
    private int mManRow;//记住搬运工所在单元格行号
    private int mManColumn;//记住搬运工所在单元格行号


    private StringBuffer[] mLabelInCells;//用StringBuffer数组实现表示局面的二维矩阵
    //   mLabelInCells是数组名字，数组中的一个元素对应的二矩阵的一行
    public GameState(String[] initialState){
        mLabelInCells = new StringBuffer[initialState.length];
        for(int i=0;i<initialState.length;i++){
            mLabelInCells[i] =new StringBuffer(initialState[i]);
        }
    }
    //getLabelInCells返回表示局面的二维矩阵
    public StringBuffer[] getLabelInCells(){
        return mLabelInCells;
    }



}
