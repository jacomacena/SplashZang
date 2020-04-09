package br.com.grupoprojetar.splashzang.Engine;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

@SuppressLint("Registered")
public class JogoZang extends Activity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        gameView = new GameView(this);
        setContentView(gameView);
    }

    @Override
    protected void onDestroy()
    {
        gameView.setGameIsRunning(false);
        gameView.stopThread();
        super.onDestroy();
    }

}
