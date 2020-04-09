package br.com.grupoprojetar.splashzang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import br.com.grupoprojetar.splashzang.Engine.JogoZang;

public class MenuPrincipal extends AppCompatActivity {

    // Variáveis
    private String dificuldade = "fácil";
    private EditText nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        nome = findViewById(R.id.nome);
    }

    // Pegando os dados digitados e a dificuldade selecionada para iniciar o jogo
    public void submitData(View v){
        Bundle dados = new Bundle();
        String NomeSet = nome.getText().toString();

        dados.putString("nome", NomeSet);
        dados.putString("dificuldade", dificuldade);

        Intent game = new Intent(getApplicationContext(), JogoZang.class);
        game.putExtras(dados);
        startActivity(game);
    }

    // Check da dificuldade
    public void radioClicked(View v){
        if (((RadioButton)v).isChecked()){
            switch (v.getId()){
                case R.id.btfacil: dificuldade = "facil";
                    break;
                case R.id.btmedio: dificuldade = "medio";
                    break;
                case R.id.btdificil: dificuldade = "dificil";
                    break;
            }
        }
    }

    // botão sair
    public void sair(View v){
        finish();
    }


}
