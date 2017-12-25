package com.example.administrator.sokoban;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Administrator on 2017/11/28.
 */

public class GameBitmaps {
    public static Bitmap ManBitmap = null;//需要为每一幅图片安排一个static变量
    public static Bitmap BoxBitmap = null;//需要为每一幅图片安排一个static变量
    public static Bitmap FlagBitmap = null;//需要为每一幅图片安排一个static变量
    public static Bitmap WallBitmap = null;//需要为每一幅图片安排一个static变量




    public static void loadGameBitmaps(Resources res){
        if (ManBitmap == null)       //如果为null加载图片；否则说明已经加载过了。
            ManBitmap = BitmapFactory.decodeResource(res, R.drawable.eggman_48x48);
        if (BoxBitmap == null)       //如果为null加载图片；否则说明已经加载过了。
            BoxBitmap = BitmapFactory.decodeResource(res, R.drawable.box);
        if (FlagBitmap == null)       //如果为null加载图片；否则说明已经加载过了。
            FlagBitmap = BitmapFactory.decodeResource(res, R.drawable.flag);
        if (WallBitmap == null)       //如果为null加载图片；否则说明已经加载过了。
            WallBitmap = BitmapFactory.decodeResource(res, R.drawable.wall);

    }
    //释放图片对象占据的内存
    public static void releaseGameBitmaps(){
        if (ManBitmap != null) {
            ManBitmap.recycle();
            ManBitmap = null;
        }
        if (BoxBitmap != null) {
            BoxBitmap.recycle();
            BoxBitmap = null;
        }
        if (FlagBitmap != null) {
            FlagBitmap.recycle();
            FlagBitmap = null;
        }
        if (WallBitmap != null) {
            WallBitmap.recycle();
            WallBitmap = null;
        }
    }


}
