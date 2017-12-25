package com.example.administrator.sokoban;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.content.res.Resources;

/**
 * Created by Administrator on 2017/11/21.
 */
public class GameView  extends View{
    private float mCellWidth;
    //    public static final int CELL_NUM_PER_LINE = 12;
    public static final int CELL_NUM_PER_LINE = 12;
    //    private Bitmap manBitmap = null;
    private int mManRow =0;
    private int mManColumn =0;

    private int mBoxRow =0;
    private int mBoxColumn = 0;
    private int mBox[][];
    private int boxIndex = 0;

    //    private int mFlagRow = 0;
//    private int mFlagColumn = 0;
    private int[][] mFlag;

    //加载声音
    private SoundPool mSoundPool;
    private final int mSoundOneSte;

    public GameActivity mGameActivity;
    public  StringBuffer[] labelInCells;

    public GameView(Context context) {
        super(context);
//        manBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.eggman_48x48);
        mGameActivity = (GameActivity) context;
        labelInCells = mGameActivity.getCurrentState().getLabelInCells();
        //位置
        get_gongren_chushi_weizhi();
        get_XiangZi_ChuShi_WeiZhi();
        get_HongQi_ChuShi_WeiZhi();
        //图片
        GameBitmaps.loadGameBitmaps(getResources());
        //音乐
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        mSoundOneSte = mSoundPool.load(mGameActivity, R.raw.onestep, 1);
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCellWidth = w/CELL_NUM_PER_LINE;
    }

    public void onDraw(Canvas canvas){
        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.background));
        canvas.drawRect(0,0,getWidth(),getHeight(),background);

        Paint linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        for (int r=0;r<=CELL_NUM_PER_LINE;r++)
            canvas.drawLine(0,r*mCellWidth,getWidth(),r*mCellWidth,linePaint);
        for (int c=0;c<=CELL_NUM_PER_LINE;c++)
            canvas.drawLine(c*mCellWidth,0,c*mCellWidth,CELL_NUM_PER_LINE*mCellWidth,linePaint);

//        Rect srcRect = new Rect(0,0,GameBitmaps.ManBitmap.getWidth(),GameBitmaps.ManBitmap.getHeight());
////        Rect descRect = new Rect(0,0,(int)mCellWidth,(int)mCellWidth);
//        Rect descRect = getRect(mManRow,mManColumn);
//        canvas.drawBitmap(GameBitmaps.ManBitmap,srcRect,descRect,null);
//
//        Resources res = GameView.this.getResources() ;
//        Bitmap bitmap = BitmapFactory.decodeResource(res,R.drawable.chrysanthemum);
//        Rect sboxRect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
//        Rect dboxRect = getRect(mBoxRow,mBoxColumn);
//        canvas.drawBitmap(bitmap,sboxRect,dboxRect,null);


//        if (labelInCells[mBoxRow ].charAt(mBoxColumn - 1) != 'W')


        //绘制红旗
        for (int i = 0;i<mFlag.length;i++) {
            Log.d("GameView",i+" iii");
            Rect flagRect = new Rect(0, 0, GameBitmaps.FlagBitmap.getWidth(), GameBitmaps.FlagBitmap.getHeight());
            Rect destRect = getRect(mFlag[i][0], mFlag[i][1]);
            canvas.drawBitmap(GameBitmaps.FlagBitmap, flagRect, destRect, null);
        }
        //根据游戏局面绘制游戏界面
        drawGameBoard(canvas);

        //如果过关了，要报告过关
        if (isGameOver()){
            Paint txtPaint = new Paint();
            txtPaint.setColor(Color.RED);
            txtPaint.setTextSize(150.0f);
            //报告过关了
            canvas.drawText(getContext().getString(R.string.txt_game_over),3*mCellWidth,6*mCellWidth,txtPaint);
        }

    }

    private boolean isGameOver() {
//        if (mBoxColumn == mFlagColumn && mBoxRow == mFlagRow){
//            return  true;
//        }

        int count = 0;
        for (int i = 0;i<mBox.length;i++){
            for (int j = 0;j<mFlag.length;j++){
                if (mBox[i][0]==mFlag[j][0]&&mBox[i][1]==mFlag[j][1]){
                    count++;
                    break;
                }
            }
        }

        if (count == mBox.length){
            return true;
        }

        return false;
    }


    private void drawGameBoard(Canvas canvas) {
        Rect srcRect;
        Rect destRect;
//        StringBuffer[] labelInCells = mGameActivity.getCurrentState().getLabelInCells();
        for (int i=0;i<labelInCells.length;i++){
            for (int c = 0 ; c<labelInCells[i].length();c++){
                destRect = getRect(i,c);
                switch (labelInCells[i].charAt(c)){
                    case 'W':
                        srcRect = new Rect(0,0,GameBitmaps.WallBitmap.getWidth(),GameBitmaps.WallBitmap.getHeight());
                        canvas.drawBitmap(GameBitmaps.WallBitmap,srcRect,destRect,null);
                        break;
                    case 'B':
                        srcRect = new Rect(0,0,GameBitmaps.BoxBitmap.getWidth(),GameBitmaps.BoxBitmap.getHeight());
                        canvas.drawBitmap(GameBitmaps.BoxBitmap,srcRect,destRect,null);
                        break;
                    case 'M':
                        srcRect = new Rect(0,0,GameBitmaps.ManBitmap.getWidth(),GameBitmaps.ManBitmap.getHeight());
                        canvas.drawBitmap(GameBitmaps.ManBitmap,srcRect,destRect,null);
                        break;
                    case 'F':
                        srcRect = new Rect(0,0,GameBitmaps.FlagBitmap.getWidth(),GameBitmaps.FlagBitmap.getHeight());
                        canvas.drawBitmap(GameBitmaps.FlagBitmap,srcRect,destRect,null);
                        break;
                }
            }
        }
    }

    //根据游戏开局初始化搬运工的位置
    public void get_gongren_chushi_weizhi() {
//         StringBuffer[] labelInCells = mGameActivity.getCurrentState().getLabelInCells(); //得到表示局面的二维矩阵
        for (int r = 0; r < GameView.CELL_NUM_PER_LINE; r++) //逐行地扫描
            for (int c = 0; c < GameView.CELL_NUM_PER_LINE; c++){ //对当前行，逐列地扫描
                if (labelInCells[r].charAt(c) == 'M') { //找到搬运工所在位置
                    mManRow = r; //记住搬运工的行号
                    mManColumn = c; //记住搬运工的列号
                    return; //停止扫描。函数返回。
                }
            }
    }

    //根据开局初始化箱子的位置
    public void get_XiangZi_ChuShi_WeiZhi() {
        int temp = 0;
//        StringBuffer[] labelInCells = mGameActivity.getCurrentState().getLabelInCells(); //得到表示局面的二维矩阵
        for (int r = 0; r < GameView.CELL_NUM_PER_LINE; r++) //从上到下扫描一行
            for (int c = 0; c < GameView.CELL_NUM_PER_LINE; c++) { //对当前行，从左到右扫描
                if (labelInCells[r].charAt(c) == 'B') { //遇到箱子
//                 mBoxRow = r; //记住箱子的行号
//                 mBoxColumn = c; //记住箱子的列号
//                 return; //停止扫描。返回。
                    temp++;
                }
            }

        mBox = new int[temp][2];
        temp = 0;
        for (int r = 0; r < GameView.CELL_NUM_PER_LINE; r++) //从上到下扫描一行
            for (int c = 0; c < GameView.CELL_NUM_PER_LINE; c++) { //对当前行，从左到右扫描
                if (labelInCells[r].charAt(c) == 'B') { //遇到箱子
//                 mBoxRow = r; //记住箱子的行号
//                 mBoxColumn = c; //记住箱子的列号
                    mBox[temp][0] = r;
                    mBox[temp][1] = c;
                    temp++;
                }
            }
    }

    //根据开局初始化旗子的位置
    public void get_HongQi_ChuShi_WeiZhi(){
        int temp = 0;
        for (int r=0; r<GameView.CELL_NUM_PER_LINE; r++)
            for (int c = 0; c < GameView.CELL_NUM_PER_LINE; c++){
                if (labelInCells[r].charAt(c) == 'F'){
//                    mFlagRow = r;
//                    mFlagColumn = c;
                    ++temp;
                }
            }

        mFlag = new int[temp][2];
        temp = 0;
        for (int r=0; r<GameView.CELL_NUM_PER_LINE; r++)
            for (int c = 0; c < GameView.CELL_NUM_PER_LINE; c++){
                if (labelInCells[r].charAt(c) == 'F'){
//                    mFlagRow = r;
//                    mFlagColumn = c;
                    mFlag[temp][0]=r;
                    mFlag[temp][1]=c;
                    Log.d("GameView",mFlag[temp][0]+" "+mFlag[temp][1]);
                    temp++;
                }
            }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(isGameOver()){
            return false;
        }
        if (event.getAction() != MotionEvent.ACTION_DOWN)
            return true;
        int touch_x = (int) event.getX();
        int touch_y = (int) event.getY();

        if (touch_below_to_man(touch_x, touch_y, mManRow, mManColumn)) {
            handleDown();
        }
        if (touch_up_to_man(touch_x, touch_y, mManRow, mManColumn)){
            handleUp();
        }
        if (touch_right_to_man(touch_x, touch_y, mManRow, mManColumn)){
            handleRight();
        }
        if (touch_left_to_man(touch_x, touch_y, mManRow, mManColumn)){
            handleLeft();
        }

        postInvalidate();

        return true;
    }

    private void handleLeft() {
        if (isBoxLeftMan()) {
            if (mBoxColumn >0
                    && labelInCells[mBoxRow ].charAt(mBoxColumn - 1) != 'W'
                    && labelInCells[mBoxRow].charAt(mBoxColumn- 1) != 'B') {
                labelInCells[mManRow].setCharAt(mManColumn, ' ');
                labelInCells[mBoxRow].setCharAt(mBoxColumn, ' ');
                mBoxColumn--;
                mManColumn--;
                mBox[boxIndex][1] = mBoxColumn;
                labelInCells[mManRow].setCharAt(mManColumn, 'M');
                labelInCells[mBoxRow].setCharAt(mBoxColumn, 'B');
                mSoundPool.play(mSoundOneSte,(float)0.5, (float)0.5, 1, 0, 1f);
            }
        } else if (mManColumn >0&& labelInCells[mManRow ].charAt(mManColumn- 1) != 'W') {
            labelInCells[mManRow].setCharAt(mManColumn, ' ');
            mManColumn--;
            labelInCells[mManRow].setCharAt(mManColumn, 'M');
            mSoundPool.play(mSoundOneSte,(float)0.5, (float)0.5, 1, 0, 1f);
        }
    }

    private void handleRight() {
        if (isBoxRightMan()) {
            if (mBoxColumn + 1 < CELL_NUM_PER_LINE
                    && labelInCells[mBoxRow ].charAt(mBoxColumn+1) != 'W'
                    && labelInCells[mBoxRow ].charAt(mBoxColumn+1) != 'B') {
                labelInCells[mManRow].setCharAt(mManColumn,' ');
                labelInCells[mBoxRow].setCharAt(mBoxColumn,' ');
                mBoxColumn++;
                mManColumn++;
                mBox[boxIndex][1] = mBoxColumn;
                labelInCells[mManRow].setCharAt(mManColumn,'M');
                labelInCells[mBoxRow].setCharAt(mBoxColumn,'B');
                mSoundPool.play(mSoundOneSte,(float)0.5, (float)0.5, 1, 0, 1f);
            }
        }else if (mManColumn + 1 < CELL_NUM_PER_LINE
                && labelInCells[mManRow].charAt(mManColumn+1) != 'W'){
            labelInCells[mManRow].setCharAt(mManColumn,' ');
            mManColumn++;
            labelInCells[mManRow].setCharAt(mManColumn,'M');
            mSoundPool.play(mSoundOneSte,(float)0.5, (float)0.5, 1, 0, 1f);
        }
    }

    private void handleUp() {
        if (isBoxUpMan()) {
            if (mBoxRow>0
                    && labelInCells[mBoxRow - 1].charAt(mBoxColumn) != 'W'
                    && labelInCells[mBoxRow - 1].charAt(mBoxColumn) != 'B') {
                labelInCells[mManRow].setCharAt(mManColumn,' ');
                labelInCells[mBoxRow].setCharAt(mBoxColumn,' ');
                mBoxRow--;
                mManRow--;
                mBox[boxIndex][0] = mBoxRow;
                labelInCells[mManRow].setCharAt(mManColumn,'M');
                labelInCells[mBoxRow].setCharAt(mBoxColumn,'B');
                mSoundPool.play(mSoundOneSte,(float)0.5, (float)0.5, 1, 0, 1f);
            }
        }else if (mManRow >0 && labelInCells[mManRow - 1].charAt(mManColumn) != 'W') {
            labelInCells[mManRow].setCharAt(mManColumn,' ');
            mManRow--;
            labelInCells[mManRow].setCharAt(mManColumn,'M');
            mSoundPool.play(mSoundOneSte,(float)0.5, (float)0.5, 1, 0, 1f);
        }
    }

    private void handleDown() {
        if (isBoxBlowMan()) {
            if (mBoxRow + 1 < CELL_NUM_PER_LINE
                    && labelInCells[mBoxRow + 1].charAt(mBoxColumn) != 'W'
                    && labelInCells[mBoxRow + 1].charAt(mBoxColumn) != 'B') {
                labelInCells[mManRow].setCharAt(mManColumn,' ');
                labelInCells[mBoxRow].setCharAt(mBoxColumn,' ');
                mBoxRow++;
                mManRow++;
                mBox[boxIndex][0] = mBoxRow;
                labelInCells[mManRow].setCharAt(mManColumn,'M');
                labelInCells[mBoxRow].setCharAt(mBoxColumn,'B');
                mSoundPool.play(mSoundOneSte,(float)0.5, (float)0.5, 0, 0, 1f);
            }
        } else if (mManRow + 1 < CELL_NUM_PER_LINE && labelInCells[mManRow + 1].charAt(mManColumn) != 'W'){
            labelInCells[mManRow].setCharAt(mManColumn,' ');
            mManRow++;
            labelInCells[mManRow].setCharAt(mManColumn,'M');
            mSoundPool.play(mSoundOneSte,(float)0.5, (float)0.5, 1, 0, 1f);
        }
    }

    private  boolean isBoxBlowMan(){
        for (int i = 0;i<mBox.length;i++){
            if (mBox[i][0] == mManRow + 1 && mBox[i][1] == mManColumn){
                mBoxRow = mBox[i][0];
                mBoxColumn = mBox[i][1];
                boxIndex = i;
                return true;
            }
        }
//        return mBoxColumn == mManColumn && mBoxRow == mManRow+1;
        return false;
    }

    private  boolean isBoxLeftMan(){
        for (int i = 0;i<mBox.length;i++){
            if (mBox[i][0] == mManRow && mBox[i][1] == mManColumn - 1){
                mBoxRow = mBox[i][0];
                mBoxColumn = mBox[i][1];
                boxIndex = i;
                return true;
            }
        }
//        return mBoxRow == mManRow && mBoxColumn == mManColumn-1;
        return false;
    }
    private  boolean isBoxRightMan(){
        for (int i = 0;i<mBox.length;i++){
            if (mBox[i][0] == mManRow && mBox[i][1] == mManColumn+1){
                mBoxRow = mBox[i][0];
                mBoxColumn = mBox[i][1];
                boxIndex = i;
                return true;
            }
        }
//        return mBoxRow == mManRow && mBoxColumn == mManColumn+1;
        return false;
    }
    private  boolean isBoxUpMan(){
        for (int i = 0;i<mBox.length;i++){
            if (mBox[i][0] == mManRow -1 && mBox[i][1] == mManColumn){
                mBoxRow = mBox[i][0];
                mBoxColumn = mBox[i][1];
                boxIndex = i;
                return true;
            }
        }
//        return mBoxColumn == mManColumn && mBoxRow == mManRow-1;
        return false;
    }

    private boolean touch_left_to_man(int touch_x, int touch_y, int manRow, int manColumn) {
        int leftColumn = manColumn - 1;
        Rect leftRect = getRect(manRow, leftColumn);
        return leftRect.contains(touch_x, touch_y);
    }

    private boolean touch_right_to_man(int touch_x, int touch_y, int manRow, int manColumn) {
        int rightColumn = manColumn + 1;
        Rect rightRect = getRect(manRow, rightColumn);
        return rightRect.contains(touch_x, touch_y);
    }

    private boolean touch_below_to_man(int touch_x, int touch_y,int manRow,int manColumn) {
        int belowRow = manRow + 1;
        Rect belowRect = getRect(belowRow,manColumn);
        return belowRect.contains(touch_x,touch_y);
    }
    private boolean touch_up_to_man(int touch_x, int touch_y,int manRow,int manColumn) {
        int upRow = manRow - 1;
        Rect upRect = getRect(upRow,manColumn);
        return upRect.contains(touch_x,touch_y);
    }

    private  Rect getRect(int row,int column){
        int left = (int) (column*mCellWidth);
        int top = (int) (row*mCellWidth);
        int right = (int) ((column+1)*mCellWidth);
        int buttom = (int) ((row +1)*mCellWidth);
        return  new Rect(left,top,right,buttom);

    }
}
