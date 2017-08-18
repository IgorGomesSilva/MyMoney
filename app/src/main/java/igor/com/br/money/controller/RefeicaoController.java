package igor.com.br.money.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import igor.com.br.money.entidades.Refeicao;
import igor.com.br.money.helper.CadastrarHelperRefeicao;
import igor.com.br.money.dao.RefeicaoDAO;
import igor.com.br.money.R;
import igor.com.br.money.entidades.Refeicao;
import igor.com.br.money.entidades.Main_inicio;

public class RefeicaoController extends AppCompatActivity {

    private CadastrarHelperRefeicao helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refeicaocontroller);

        helper = new CadastrarHelperRefeicao(this);

        Button btSalvar = (Button)findViewById(R.id.btSalvar);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Refeicao refeicao = helper.PegaItensCadastro();

                RefeicaoDAO dao = new RefeicaoDAO(RefeicaoController.this);
                dao.salva(refeicao);
                dao.close();

                finish();

            }
        });



    }
    public void onBackPressed()
    {
        Intent mudarTela = new
                Intent(RefeicaoController.this,Main_inicio.class);
        RefeicaoController.this.startActivity(mudarTela);
        RefeicaoController.this.finish();
    }

}
