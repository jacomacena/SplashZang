package com.example.jaco.splashzang.Engine;

import android.view.View;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import java.util.Random;
import com.example.jaco.splashzang.BD.RecordeService;
import com.example.jaco.splashzang.GameOver;
import com.example.jaco.splashzang.R;

/**
 * Created by jaco on 12/07/16.
 */
public class GameView extends View {

    //Variaveis
    private Bitmap fundo;
    private Bitmap timer;
    private Bitmap score;
    private Bitmap over;
    private int width,height;
    private Paint paint;
    private Paint paintover;

    //Game loop
    private int time = 60;
    private long pontos = 0L;
    private long recorde;
    private RecordeService recordeService;
    private boolean game_is_running;
    private String nome;

    //Falling Object
    private Object bom[] = new Object[9];
    private Object mau[] = new Object[9];

    //Threads
    private TimerThread timer_thread;
    private DrawingThread drawing_thread;

    //Thread para correr o tempo
    private class TimerThread extends Thread {
        @Override
        public void run() {
            while (game_is_running){
                if (time > 0) {
                    try {
                        sleep(1000);
                        time--;
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    game_is_running = false;
                    postInvalidate();
                }

            }
        }
    }

    //Thread para exibição dos objetos caindo na tela
    private class DrawingThread extends Thread {
        @Override
        public void run() {
            while (game_is_running){
                try {
                    sleep(17);
                    postInvalidate();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public GameView(Context context) {
        super(context);

        //Recuperando dados selecionados e digitados pelo usuário
        Bundle b = ((Activity)context).getIntent().getExtras();
        nome  = b.getString("nome");
        String dificuldade = b.getString("dificuldade");

        //validando a velocidade do jogo
        int falling_speed;
        if (dificuldade.equals("facil")) {
            falling_speed = 3;

        } else if (dificuldade.equals("medio")) {
            falling_speed = 6;

        } else {
            falling_speed = 9;

        }

        Resources res;
        res = context.getResources();

        // recuperando imagens do drawable
        Bitmap fundo_nr = BitmapFactory.decodeResource(res, R.drawable.background);
        Bitmap timer_nr = BitmapFactory.decodeResource(res, R.drawable.timer);
        Bitmap score_nr = BitmapFactory.decodeResource(res, R.drawable.score);
        Bitmap over_nr = BitmapFactory.decodeResource(res, R.drawable.over);
        Bitmap good_nr = BitmapFactory.decodeResource(res, R.drawable.good);
        Bitmap bad_nr = BitmapFactory.decodeResource(res, R.drawable.space_invader);

        //verificando o tamanho da tela do device do usuario
        DisplayMetrics metrics = res.getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        float scalefactor = (float) width/(float) fundo_nr.getWidth();

        //desenhando na tela as informações recuperadas
        fundo = Bitmap.createScaledBitmap(fundo_nr,width,height,false);
        timer = Bitmap.createScaledBitmap(timer_nr,(int)(timer_nr.getWidth()*scalefactor),(int) (timer_nr.getHeight()*scalefactor),false);
        score = Bitmap.createScaledBitmap(score_nr,(int)(score_nr.getWidth()*scalefactor),(int) (score_nr.getHeight()*scalefactor),false);
        over = Bitmap.createScaledBitmap(over_nr,width,height,false);
        Bitmap bad = Bitmap.createScaledBitmap(bad_nr, (int) (bad_nr.getWidth() * scalefactor), (int) (bad_nr.getHeight() * scalefactor), false);
        Bitmap good = Bitmap.createScaledBitmap(good_nr, (int) (good_nr.getWidth() * scalefactor), (int) (good_nr.getHeight() * scalefactor), false);

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        paint.setTextSize(40 * scalefactor);

        paintover = new Paint();
        paintover.setStyle(Paint.Style.FILL);
        paintover.setAntiAlias(true);
        paintover.setFakeBoldText(true);
        paintover.setColor(Color.WHITE);
        paintover.setTextSize(50 * scalefactor);

        Random rand = new Random();

        for (int i=0; i<9; i++) {
            bom[i] = new Object(0,0, falling_speed, good, rand);
            mau[i] = new Object(0,0, falling_speed, bad, rand);

            if (bom[i] == mau[i]){
                bom[i].resetPos(width,height);
                mau[i].resetPos(width,height);
            }else{
                bom[i].resetPos(width,height);
                mau[i].resetPos(width,height);
            }


        }

        /* Threads */
        game_is_running = true;
        timer_thread = new TimerThread();
        drawing_thread = new DrawingThread();
        timer_thread.start();
        drawing_thread.start();

        recorde = recordeService.getRecorde();
    }

    //Metodo Draw para criar os desenhos
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        if (game_is_running) {
            canvas.drawBitmap(fundo, 0, 0, null);
            canvas.drawBitmap(timer, 0, canvas.getHeight() - timer.getHeight(), null);
            canvas.drawBitmap(score, canvas.getWidth() - score.getWidth(), 0, null);
            canvas.drawText("" + pontos, (float) (canvas.getWidth() - (float) score.getWidth() * 0.6), (float) (score.getHeight() * 0.7), paint);
            canvas.drawText("" + time, (float) (timer.getWidth() * 0.4), (float) (canvas.getHeight() - timer.getHeight() * 0.40), paint);

            for( int i=0; i<9; i++) {
                    bom[i].update();
                    if (bom[i].getY() >= canvas.getHeight()){
                        bom[i].resetPos(width,height);
                    }
                    bom[i].draw(canvas);

                    mau[i].update();
                    if (mau[i].getY() >= canvas.getHeight()){
                        mau[i].resetPos(width,height);
                    }
                    mau[i].draw(canvas);
            }

        }else {
            canvas.drawBitmap(over,0,0,null);
            canvas.drawText(nome + ", sua pontuação foi:", (float) (canvas.getWidth() / 5), (float) (canvas.getHeight() / 1.7), paintover);
            canvas.drawText(pontos + " pontos!", (float) (canvas.getWidth() / 5), (float) (canvas.getHeight() / 1.5), paintover);
            atualizaRecorde();
        }
    }

    public void atualizaRecorde(){
        if (this.pontos > this.recorde){
            this.recorde = this.pontos;
            this.recordeService.novoRecorde(recorde);
        }
    }

    //Metodo para validação do toque na tela
    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event)
    {
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();

        if(action == MotionEvent.ACTION_DOWN){
            for (int i=0; i<9;i++){
                if (bom[i].isInsideBox(x,y)){
                    bom[i].resetPos(width,height);
                    pontos -= 20;
                }
                if (mau[i].isInsideBox(x,y)){
                    mau[i].resetPos(width,height);
                    pontos +=10;
                }
            }
        }

        return super.onTouchEvent(event);
    }

    //setando o jogo como running para começar a executar
    public void setGameIsRunning(boolean game_is_running){
        this.game_is_running = game_is_running;
    }

    //Parando as threads
    public void stopThread()
    {
        try {
            timer_thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        try {
            drawing_thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    //setando valores para recuperar os dados obtidos no jogo para armazenar no banco de dados
    /* public class Variavel
    {
        String nomefinal;
        int pontosfinal;

        public Variavel()
        {
            this.nomefinal = nome;
            this.pontosfinal = pontos;
        }

        public String getNome()
        {
            return nomefinal;
        }

        public int getPontos()
        {
            return pontosfinal;
        }
    } */


}
