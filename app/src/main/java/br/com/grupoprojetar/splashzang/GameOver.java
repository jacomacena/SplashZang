package br.com.grupoprojetar.splashzang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
    }

    public void voltar(View v){
        Intent volta = new Intent(this, MenuPrincipal.class);
        startActivity(volta);
        finish();
    }

}
