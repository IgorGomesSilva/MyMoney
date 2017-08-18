package igor.com.br.money.entidades;

import android.os.Bundle;
import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import igor.com.br.money.controller.RefeicaoController;
import igor.com.br.money.dao.RefeicaoDAO;
import igor.com.br.money.R;

public class Refeicao extends AppCompatActivity {
    private ListView lista;
    private String id ;
    private String refeicaoNome;
    private String refeicaoData;
    private float refeicaoValor;
    private TextView texto ;
    private Refeicao refeicao;

    public String toString(){
        return (getRefeicaoData()+ "       Valor R$ : "+getRefeicaoValor() +"         "+ getRefeicaoNome() );
    }

    public String getRefeicaoNome() {
        return refeicaoNome;
    }

    public void setRefeicaoNome(String refeicaoNome) {
        this.refeicaoNome = refeicaoNome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefeicaoData() {
        return refeicaoData;
    }

    public void setRefeicaoData(String refeicaoData) {

        this.refeicaoData = refeicaoData;
    }

    public float getRefeicaoValor() {
        return refeicaoValor;
    }

    public void setRefeicaoValor(float refeicaoValor) {
        this.refeicaoValor = refeicaoValor;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refeicao);

        lista = (ListView)findViewById(R.id.listaRefeicao);
        registerForContextMenu(lista);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                refeicao = (Refeicao) adapter.getItemAtPosition(position);
                return false;
            }
        });



    }
    @Override
    protected void onResume() {
        super.onResume();
        carregalista();
    }
        public void carregalista(){

            RefeicaoDAO dao = new RefeicaoDAO(this);
            List<Refeicao> refeicaos = dao.getListaRefeicao();
            float soma = dao.getSomaRefeicao();
            dao.SomaAtual();
            dao.SomaAnterior();
            dao.close();
            int layout = android.R.layout.simple_list_item_1;
            ArrayAdapter<Refeicao> adapter = new ArrayAdapter<Refeicao>(this, layout, refeicaos);
            lista = (ListView)findViewById(R.id.listaRefeicao);
            lista.setAdapter(adapter);
            texto = (TextView)findViewById(R.id.textoRefeicao);
            texto.setText("Valor Total R$ : "+soma);




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro_refeicao, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void onCreateContextMenu(ContextMenu menu, View v , ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Excluir");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                RefeicaoDAO dao = new RefeicaoDAO(Refeicao.this);
                dao.deletar(refeicao);
                dao.close();
                carregalista();

                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int ItemClicado = item.getItemId();

        switch (ItemClicado){
            case R.id.novo:
                Intent irTelaCadastrar = new Intent(this, RefeicaoController.class);
                startActivity(irTelaCadastrar);
                break;

            case R.id.grahrefeicao:
                Intent irTelaGrafico = new Intent(this, Grafico.class);
                startActivity(irTelaGrafico);
                break;

            case R.id.grefeicao:
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
                Intent(Refeicao.this,Main_inicio.class);
        Refeicao.this.startActivity(mudarTela);
        Refeicao.this.finish();
    }
}
