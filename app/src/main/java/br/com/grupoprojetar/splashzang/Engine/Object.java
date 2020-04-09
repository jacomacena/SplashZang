package br.com.grupoprojetar.splashzang.Engine;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

class Object {

    //Variaveis
    private int x,y,width,height;
    private int speedY;
    private Bitmap bitmap;
    private Random rand;

    Object(int x, int y, int speedY, Bitmap bitmap, Random rand){
        this.x = x;
        this.y = y;
        this.speedY = speedY;
        this.rand = rand;
        this.bitmap = bitmap;
        this.height = bitmap.getHeight();
        this.width = bitmap.getWidth();
    }

    int getY(){
        return y;
    }

    void update(){
        y += speedY;
    }

    void draw(Canvas canvas){
        canvas.drawBitmap(bitmap,x,y,null);
    }

    boolean isInsideBox(float x, float y){
        return x >= this.x && x <= (this.x + width) && y >= this.y && y <= (this.y + height);
    }

    void resetPos(int screenW, int screenH){
        x = rand.nextInt(screenW);
        y = -rand.nextInt(screenH);
    }
}
