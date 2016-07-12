package com.example.jaco.splashzang.Engine;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by jaco on 12/07/16.
 */
public class Object {
    //Variaveis
    private int x,y,width,height;
    int speedY;
    private Bitmap bitmap;
    Random rand;

    public Object (int x, int y, int speedY, Bitmap bitmap, Random rand){
        this.x = x;
        this.y = y;
        this.speedY = speedY;
        this.rand = rand;
        this.bitmap = bitmap;
        this.height = bitmap.getHeight();
        this.width = bitmap.getWidth();
    }

    public int getY(){
        return y;
    }

    public void update(){
        y += speedY;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap,x,y,null);
    }

    public boolean isInsideBox(float x, float y){
        return x >= this.x && x <= (this.x + width) && y >= this.y && y <= (this.y + height);
    }

    public void resetPos(int screenW, int screenH){
        x = rand.nextInt(screenW);
        y = -rand.nextInt(screenH);
    }

}
