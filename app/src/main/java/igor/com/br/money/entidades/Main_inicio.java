package igor.com.br.money.entidades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.view.MenuItem;

import igor.com.br.money.R;

public class Main_inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicio);

       ImageView refeicao = (ImageView) findViewById(R.id.refeicao);

       ImageView roupa = (ImageView) findViewById(R.id.roupa);

        ImageView entre = (ImageView)findViewById(R.id.entre);

        ImageView casa = (ImageView)findViewById(R.id.casa);

        entre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irTela = new
                        Intent(Main_inicio.this,Entreterimento.class);
                Main_inicio.this.startActivity(irTela);
                Main_inicio.this.finish();
            }
        });

        roupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irTela = new
                        Intent(Main_inicio.this,Roupa.class);
                Main_inicio.this.startActivity(irTela);
                Main_inicio.this.finish();
            }
        });

        refeicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mudarTela = new
                        Intent(Main_inicio.this,Refeicao.class);
                Main_inicio.this.startActivity(mudarTela);
                Main_inicio.this.finish();
            }
        });

        casa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tela = new
                        Intent(Main_inicio.this,Conta.class);
                Main_inicio.this.startActivity(tela);
                Main_inicio.this.finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_inicio, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int ItemClicado = item.getItemId();

        switch (ItemClicado){
                case R.id.refeicao:
                    Intent irTelaRefeicao = new Intent(this, Refeicao.class);
                    startActivity(irTelaRefeicao);
                    break;
                case R.id.roupa:
                    Intent irTelaRoupa = new Intent(this, Roupa.class );
                    startActivity(irTelaRoupa);
                    break;

                case R.id.entre:
                    Intent irTelaEntre = new Intent(this, Entreterimento.class);
                    startActivity(irTelaEntre);
                    break;
                case R.id.conta:
                    Intent ir = new Intent(this,Conta.class);
                    startActivity(ir);
                    break;



                default:
                    break;
        }
        return super.onOptionsItemSelected(item);

    }
}
