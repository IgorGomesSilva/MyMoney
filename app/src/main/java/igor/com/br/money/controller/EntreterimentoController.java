package igor.com.br.money.controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import igor.com.br.money.R;
import igor.com.br.money.dao.EntreterimentoDAO;
import igor.com.br.money.entidades.Main_inicio;
import igor.com.br.money.entidades.Entreterimento;
import igor.com.br.money.helper.CadastrarHelperEntreterimento;


public class EntreterimentoController extends AppCompatActivity {

    private CadastrarHelperEntreterimento helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entreterimentocontroller);

        helper = new CadastrarHelperEntreterimento(this);

        Button btSalva = (Button) findViewById(R.id.btSalvarEntre);

        btSalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Entreterimento entre = helper.PegaDadosEntre();


                EntreterimentoDAO dao = new EntreterimentoDAO(EntreterimentoController.this);


                dao.salvaEntre(entre);
                dao.close();
                finish();

            }
        });

    }





    public void onBackPressed()
    {
        Intent mudarTela = new
                Intent(EntreterimentoController.this,Main_inicio.class);
        EntreterimentoController.this.startActivity(mudarTela);
        EntreterimentoController.this.finish();
    }

}
