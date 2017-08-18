package igor.com.br.money.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import igor.com.br.money.entidades.Conta;
import igor.com.br.money.entidades.Refeicao;

public class ContaDAO extends SQLiteOpenHelper{

    public static  float soma;
    public static float somaAnterior;
    public static  float somaAtual;

    private static final String DATABASE = "MyMoneyBD6";
    private static final int VERSAO = 3;

    public void salva(Conta conta){

        ContentValues values = new ContentValues();

        values.put("nomeContaBD",conta.getContaNome());
        values.put("valorContaBD",conta.getContaValor());
        values.put("dataContaBD",conta.getContaData());

        getWritableDatabase().insert("ContaTB",null,values);

    }

    public ContaDAO(Context context){
        super(context,DATABASE,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE ContaTB (id PRIMARY KEY," +
                "nomeContaBD TEXT , valorContaBD TEXT , dataContaBD TEXT);";


        db.execSQL(ddl);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String ddl = "DROP TABLE IF EXISTS ContaTB";

        db.execSQL(ddl);
        this.onCreate(db);

    }

    public List<Conta> getListaConta() {


        String[] colunas = {"id","nomeContaBD","valorContaBD" ,"dataContaBD"};

        Cursor cursor = getWritableDatabase().query("ContaTB",colunas,null,null,null,null,null);

        ArrayList<Conta> contas = new ArrayList<Conta>();


        while (cursor.moveToNext()) {


            Conta conta = new Conta();

            conta.setContaNome(cursor.getString(1));
            conta.setContaValor(Float.parseFloat(cursor.getString(2)));
            conta.setContaData(cursor.getString(3));


            contas.add(conta);
        }
        return contas;
    }

    public float getSomaConta() {


        Cursor cursor = getReadableDatabase().rawQuery("SELECT sum(valorContaBD) From ContaTB;",null);


        while (cursor.moveToNext()) {

            this.soma = cursor.getFloat(0);

            cursor.close();

        }
        return soma ;
    }
    public float SomaAnterior(){

        Cursor cursor = getReadableDatabase().rawQuery("SELECT sum(valorContaBD) From ContaTB where dataContaBD Between '2016-10-01' and '2016-10-31';",null);
        while (cursor.moveToNext()){
            this.somaAnterior = cursor.getFloat(0);

            cursor.close();

        }
        return somaAnterior;
    }
    public float SomaAtual(){

        Cursor cursor = getReadableDatabase().rawQuery("SELECT sum(valorContaBD) From ContaTB where dataContaBD Between '2016-11-01' and '2016-11-31';",null);
        while (cursor.moveToNext()){
            this.somaAtual = cursor.getFloat(0);

            cursor.close();

        }
        return somaAtual;

    }

    public void deletar(Conta conta ){

        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From ContaTB ;";
        db.execSQL(sql);

    }



}
