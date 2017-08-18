package igor.com.br.money.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import igor.com.br.money.R;
import igor.com.br.money.dao.ContaDAO;
import igor.com.br.money.dao.RefeicaoDAO;
import igor.com.br.money.entidades.Conta;
import igor.com.br.money.entidades.Main_inicio;
import igor.com.br.money.entidades.Refeicao;
import igor.com.br.money.helper.CadastrarHelperConta;
import igor.com.br.money.helper.CadastrarHelperRefeicao;

public class ContaController extends AppCompatActivity {

    private CadastrarHelperConta helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacontroller);

        helper = new CadastrarHelperConta(this);

        Button btSalvar = (Button)findViewById(R.id.btSalvarConta);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Conta conta = helper.PegaItensCadastro();

               ContaDAO dao = new ContaDAO(ContaController.this);
                dao.salva(conta);
                dao.close();

                finish();

            }
        });



    }
    public void onBackPressed()
    {
        Intent mudarTela = new
                Intent(ContaController.this,Main_inicio.class);
        ContaController.this.startActivity(mudarTela);
        ContaController.this.finish();
    }

}
