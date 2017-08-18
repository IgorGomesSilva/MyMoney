package igor.com.br.money.entidades;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DateSorter;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import igor.com.br.money.controller.RoupaController;
import igor.com.br.money.R;
import igor.com.br.money.dao.RoupaDAO;

public class Roupa extends AppCompatActivity {
    private  ListView listaRoupa;
    private TextView texto;
    private String id ;
    private String roupaData;
    private String roupaNome;
    private float roupaValor;
    private Roupa roupa;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString(){
        return (getRoupaData()+ "       Valor R$ : "+getRoupaValor() +"         "+ getRoupaNome() );
    }


    public void setRoupaData(String roupaData) {
        this.roupaData = roupaData;
    }

    public String getRoupaData() {
        return roupaData ;
    }

    public String getRoupaNome() {
        return roupaNome;
    }

    public void setRoupaNome(String roupaNome) {
        this.roupaNome = roupaNome;
    }

    public float getRoupaValor() {
        return roupaValor;
    }

    public void setRoupaValor(float roupaValor) {
        this.roupaValor = roupaValor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roupa);



        listaRoupa = (ListView)findViewById(R.id.listaRoupa);
        registerForContextMenu(listaRoupa);
        listaRoupa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                roupa = (Roupa) adapter.getItemAtPosition(position);
                return false;
            }
        });



    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v , ContextMenu.ContextMenuInfo menuInfo){
        MenuItem deletar =  menu.add("Excluir");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                RoupaDAO dao = new RoupaDAO(Roupa.this);
                dao.deletar(roupa);

                dao.close();
                carregaLista();

                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }
        public void carregaLista(){
        RoupaDAO dao = new RoupaDAO(this);
        List<Roupa> roupas = dao.getListaRoupa();
            dao.SomaAtual();
            dao.getSomaAnterior();
        float soma = dao.getSomaRoupa();
        dao.close();

        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter<Roupa> adapter = new ArrayAdapter<Roupa>(this, layout, roupas);
        listaRoupa = (ListView)findViewById(R.id.listaRoupa);
        listaRoupa.setAdapter(adapter);
        texto = (TextView)findViewById(R.id.texto);
        texto.setText("Valor Total R$ : "+soma);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro_roupa, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int ItemClicado = item.getItemId();

        switch (ItemClicado){
            case R.id.novo:
                Intent irTelaCadastrar = new Intent(this, RoupaController.class);
                startActivity(irTelaCadastrar);
                break;

            case R.id.grahproupa:
                Intent irTelaGrafico = new Intent(this, Grafico.class);
                startActivity(irTelaGrafico);
                break;

            case R.id.groupa:
                Intent irTelaGraficoAnterior = new Intent(this, GraficoAnterior.class);
                startActivity(irTelaGraficoAnterior);
                break;


            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }
    public void onBackPressed()
    {
        Intent mudarTela = new
                Intent(Roupa.this,Main_inicio.class);
        Roupa.this.startActivity(mudarTela);
        Roupa.this.finish();
    }







}
