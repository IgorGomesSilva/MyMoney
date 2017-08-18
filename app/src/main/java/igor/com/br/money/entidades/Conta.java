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

import java.util.List;

import igor.com.br.money.R;
import igor.com.br.money.controller.ContaController;
import igor.com.br.money.controller.RefeicaoController;
import igor.com.br.money.dao.ContaDAO;
import igor.com.br.money.dao.RefeicaoDAO;

public class Conta extends AppCompatActivity {
    private ListView listaConta;
    private String id ;
    private String ContaNome;
    private String ContaData;
    private float ContaValor;
    private TextView texto ;
    private Conta conta;

    public String toString(){
        return (getContaData()+ "       Valor R$ : "+getContaValor() +"         "+ getContaNome() );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContaNome() {
        return ContaNome;
    }

    public void setContaNome(String contaNome) {
        ContaNome = contaNome;
    }

    public String getContaData() {
        return ContaData;
    }

    public void setContaData(String contaData) {
        ContaData = contaData;
    }

    public float getContaValor() {
        return ContaValor;
    }

    public void setContaValor(float contaValor) {
        ContaValor = contaValor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conta);

        listaConta = (ListView)findViewById(R.id.listaConta);
        registerForContextMenu(listaConta);
        listaConta.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                conta = (Conta) adapter.getItemAtPosition(position);
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

            ContaDAO dao = new ContaDAO(this);
            List<Conta> contas = dao.getListaConta();
            float soma = dao.getSomaConta();
            dao.SomaAtual();
            dao.SomaAnterior();
            dao.close();
            int layout = android.R.layout.simple_list_item_1;
            ArrayAdapter<Conta> adapter = new ArrayAdapter<Conta>(this, layout, contas);
            listaConta = (ListView)findViewById(R.id.listaConta);
            listaConta.setAdapter(adapter);
            texto = (TextView)findViewById(R.id.textoConta);
            texto.setText("Valor Total R$ : "+soma);




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro_conta, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void onCreateContextMenu(ContextMenu menu, View v , ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Excluir");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
               ContaDAO dao = new ContaDAO(Conta.this);
                dao.deletar(conta);
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
                Intent irTelaCadastrar = new Intent(this, ContaController.class);
                startActivity(irTelaCadastrar);
                break;

            case R.id.grahconta:
                Intent irTelaGrafico = new Intent(this, Grafico.class);
                startActivity(irTelaGrafico);
                break;

            case R.id.gconta:
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
                Intent(Conta.this,Main_inicio.class);
        Conta.this.startActivity(mudarTela);
        Conta.this.finish();
    }
}
