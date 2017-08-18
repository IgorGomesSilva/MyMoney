package igor.com.br.money.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import igor.com.br.money.entidades.Refeicao;

public class RefeicaoDAO extends SQLiteOpenHelper{

    public static  float soma;
    public static float somaAnterior;
    public static  float somaAtual;

    private static final String DATABASE = "MyMoneyBD";
    private static final int VERSAO = 2;

    public void salva(Refeicao refeicao){

        ContentValues values = new ContentValues();

        values.put("nomeRefeicaoBD",refeicao.getRefeicaoNome());
        values.put("valorRefeicaoBD",refeicao.getRefeicaoValor());
        values.put("dataRefeicaoBD",refeicao.getRefeicaoData());

        getWritableDatabase().insert("RefeicaoTB",null,values);

    }

    public RefeicaoDAO(Context context){
        super(context,DATABASE,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE RefeicaoTB (id PRIMARY KEY," +
                "nomeRefeicaoBD TEXT , valorRefeicaoBD TEXT , dataRefeicaoBD TEXT);";


        db.execSQL(ddl);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String ddl = "DROP TABLE IF EXISTS RefeicaoTB";

        db.execSQL(ddl);
        this.onCreate(db);

    }

    public List<Refeicao> getListaRefeicao() {


        String[] colunas = {"id","nomeRefeicaoBD","valorRefeicaoBD" ,"dataRefeicaoBD"};

        Cursor cursor = getWritableDatabase().query("RefeicaoTB",colunas,null,null,null,null,null);

        ArrayList<Refeicao> refeicaos = new ArrayList<Refeicao>();


        while (cursor.moveToNext()) {


            Refeicao refeicao = new Refeicao();

            refeicao.setRefeicaoNome(cursor.getString(1));
            refeicao.setRefeicaoValor(Float.parseFloat(cursor.getString(2)));
            refeicao.setRefeicaoData(cursor.getString(3));


            refeicaos.add(refeicao);
        }
        return refeicaos;
    }

    public float getSomaRefeicao() {

        String[] valor = {"valorRefeicaoBD"};

        Cursor cursor = getReadableDatabase().rawQuery("SELECT sum(valorRefeicaoBD) From RefeicaoTB;",null);


        while (cursor.moveToNext()) {

            this.soma = cursor.getFloat(0);

            cursor.close();

        }
        return soma ;
    }
    public float SomaAnterior(){

        Cursor cursor = getReadableDatabase().rawQuery("SELECT sum(valorRefeicaoBD) From RefeicaoTB where dataRefeicaoBD Between '2016-10-01' and '2016-10-31';",null);
        while (cursor.moveToNext()){
            this.somaAnterior = cursor.getFloat(0);

            cursor.close();

        }
        return somaAnterior;
    }
    public float SomaAtual(){

        Cursor cursor = getReadableDatabase().rawQuery("SELECT sum(valorRefeicaoBD) From RefeicaoTB where dataRefeicaoBD Between '2016-11-01' and '2016-11-31';",null);
        while (cursor.moveToNext()){
            this.somaAtual = cursor.getFloat(0);

            cursor.close();

        }
        return somaAtual;

    }

    public void deletar(Refeicao refeicao ){

        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From RefeicaoTB ;";
        db.execSQL(sql);

    }



}
