package igor.com.br.money.entidades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import igor.com.br.money.R;
import igor.com.br.money.controller.EntreterimentoController;
import igor.com.br.money.controller.RefeicaoController;
import igor.com.br.money.dao.EntreterimentoDAO;

public class Entreterimento extends AppCompatActivity {

    private String nomeEntre ;
    private float valorEntre ;
    private String dataEntre ;
    private long id ;
    private TextView textoEntre ;
    private ListView listaEntre ;

    private Entreterimento entreterimento;


    public String toString(){
        return (getDataEntre()+"      Valor R$:  "+getValorEntre()+"      "+getNomeEntre());
    }


    public String getNomeEntre() {
        return nomeEntre;
    }

    public void setNomeEntre(String nomeEntre) {
        this.nomeEntre = nomeEntre;
    }

    public float getValorEntre() {
        return valorEntre;
    }

    public void setValorEntre(float valorEntre) {
        this.valorEntre = valorEntre;
    }

    public String getDataEntre() {

        return dataEntre;
    }

    public void setDataEntre(String dataEntre) {

            this.dataEntre = dataEntre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entreterimento);

        listaEntre = (ListView)findViewById(R.id.listaEntre);
        registerForContextMenu(listaEntre);
        listaEntre.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                entreterimento = (Entreterimento) adapter.getItemAtPosition(position);
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
                EntreterimentoDAO dao = new EntreterimentoDAO(Entreterimento.this);
                dao.deletar(entreterimento);

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
        EntreterimentoDAO dao = new EntreterimentoDAO(this);
        List<Entreterimento> entres = dao.getListaEntre();
        float soma = dao.getSomaEntre();
            dao.SomaAnterior();
            dao.SomaAtual();
        dao.close();
        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter<Entreterimento> adapter = new ArrayAdapter<Entreterimento>(this,layout,entres);
        listaEntre = (ListView)findViewById(R.id.listaEntre);
        listaEntre.setAdapter(adapter);
        textoEntre = (TextView)findViewById(R.id.textoEntre);
        textoEntre.setText("Valor Total R$ : "+soma);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro_entreterimento, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int ItemClicado = item.getItemId();

        switch (ItemClicado){
            case R.id.novo:
                Intent irTelaCadastrar = new Intent(this, EntreterimentoController.class);
                startActivity(irTelaCadastrar);
                break;

            case R.id.grahentre:
                Intent irTelaEntre = new Intent(this, Grafico.class);
                startActivity(irTelaEntre);
                break;

            case R.id.gEntre:
                Intent ir = new Intent(this, GraficoAnterior.class);
                startActivity(ir);
                break;


            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }
    public void onBackPressed()
    {
        Intent mudarTela = new
                Intent(Entreterimento.this,Main_inicio.class);
        Entreterimento.this.startActivity(mudarTela);
        Entreterimento.this.finish();
    }
}
