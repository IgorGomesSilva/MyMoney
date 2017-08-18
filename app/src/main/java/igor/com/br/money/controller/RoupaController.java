package igor.com.br.money.controller;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import igor.com.br.money.R;
import igor.com.br.money.dao.RoupaDAO;
import igor.com.br.money.entidades.Main_inicio;
import igor.com.br.money.entidades.Roupa;
import igor.com.br.money.helper.CadastrarHelperRoupa;

public class RoupaController extends AppCompatActivity {

    private CadastrarHelperRoupa helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roupacontroller);



        helper = new CadastrarHelperRoupa(this);

        Button btSalvar = (Button)findViewById(R.id.btSalvarRoupa);


        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Roupa roupa = helper.PegaDados();

                RoupaDAO dao = new RoupaDAO(RoupaController.this);
                dao.salvaRoupa(roupa);
                dao.close();

                finish();
            }
        });
    }





    public void onBackPressed()
    {
        Intent mudarTela = new
                Intent(RoupaController.this,Main_inicio.class);
        RoupaController.this.startActivity(mudarTela);
        RoupaController.this.finish();
    }

}
