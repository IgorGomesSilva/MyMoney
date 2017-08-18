package igor.com.br.money.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import igor.com.br.money.entidades.Entreterimento;
import igor.com.br.money.entidades.Roupa;

public class EntreterimentoDAO extends SQLiteOpenHelper{

    public  static float soma ;
    public static float somaAnterior ;
    public static float somaAtual;
    private static final String DATABASE = "MyMoneyBD5";
    private static final int VERSAO = 1;

    public void salvaEntre (Entreterimento entreterimento){

        ContentValues values = new ContentValues();


        values.put("nomeEntreBD",entreterimento.getNomeEntre());
        values.put("valorEntreBD",entreterimento.getValorEntre());
        values.put("dataEntreBD", entreterimento.getDataEntre());

        getWritableDatabase().insert("EntreTB",null,values);

    }

    public EntreterimentoDAO(Context context){
        super(context,DATABASE,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE EntreTB (id PRIMARY KEY," +
                "nomeEntreBD TEXT , valorEntreBD REAL , dataEntreBD TEXT);";


        db.execSQL(ddl);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String ddl = "DROP TABLE IF EXISTS EntreTB";

        db.execSQL(ddl);
        this.onCreate(db);

    }
    public List<Entreterimento> getListaEntre() {


        String[] colunas = {"id","nomeEntreBD","valorEntreBD" ,"dataEntreBD"};

        Cursor cursor = getWritableDatabase().query("EntreTB",colunas,null,null,null,null,null);

        ArrayList<Entreterimento> entres = new ArrayList<Entreterimento>();


        while (cursor.moveToNext()) {


             Entreterimento entre = new Entreterimento();

            entre.setNomeEntre(cursor.getString(1));
            entre.setValorEntre(Float.parseFloat(cursor.getString(2)));
            entre.setDataEntre(cursor.getString(3));

            entres.add(entre);
        }
        return entres;
    }

    public float getSomaEntre() {

        String[] valor = {"valorEntreBD"};

        Cursor cursor = getReadableDatabase().rawQuery("SELECT sum(valorEntreBD) From EntreTB;",null);


        while (cursor.moveToNext()) {

            this.soma = cursor.getFloat(0);

            cursor.close();

        }
        return soma;
    }

    public float SomaAnterior(){

        Cursor cursor = getReadableDatabase().rawQuery("SELECT sum(valorEntreBD) From EntreTB where dataEntreBD Between '2016-10-01' and '2016-10-31';",null);
        while (cursor.moveToNext()){
            this.somaAnterior = cursor.getFloat(0);

            cursor.close();

        }
        return somaAnterior;
    }
    public float SomaAtual(){

        Cursor cursor = getReadableDatabase().rawQuery("SELECT sum(valorEntreBD) From EntreTB where dataEntreBD Between '2016-11-01' and '2016-11-31';",null);
        while (cursor.moveToNext()){
            this.somaAtual = cursor.getFloat(0);

            cursor.close();

        }
        return somaAtual;

    }


    public void deletar(Entreterimento entreterimento ){

        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From EntreTB ;";
        db.execSQL(sql);

    }
}



