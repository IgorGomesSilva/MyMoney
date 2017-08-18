package igor.com.br.money.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import igor.com.br.money.entidades.Grafico;
import igor.com.br.money.entidades.Roupa;

import static java.lang.String.valueOf;

public class RoupaDAO extends SQLiteOpenHelper{

    public static  float soma ;

    public static float somaAtual;
    public static float somaAnterior;

    private static final String DATABASE = "MyMoneyBD0";
    private static final int VERSAO = 3;

    public void salvaRoupa (Roupa roupa){

        ContentValues values = new ContentValues();

        values.put("nomeRoupaBD",roupa.getRoupaNome());
        values.put("valorRoupaBD",roupa.getRoupaValor());
        values.put("dataRoupaBD", roupa.getRoupaData());

        getWritableDatabase().insert("RoupaTB",null,values);

    }

    public RoupaDAO(Context context){
        super(context,DATABASE,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE RoupaTB (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nomeRoupaBD TEXT , valorRoupaBD REAL , dataRoupaBD TEXT);";


        db.execSQL(ddl);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String ddl = "DROP TABLE IF EXISTS RoupaTB";

        db.execSQL(ddl);
        this.onCreate(db);

    }
    public List<Roupa> getListaRoupa() {


        String[] colunas = {"id","nomeRoupaBD","valorRoupaBD" ,"dataRoupaBD"};

        Cursor cursor = getWritableDatabase().query("RoupaTB",colunas,null,null,null,null,null);

        ArrayList<Roupa> roupas = new ArrayList<Roupa>();


        while (cursor.moveToNext()) {


             Roupa roupa = new Roupa();

            roupa.setRoupaNome(cursor.getString(1));
            roupa.setRoupaValor(Float.parseFloat(cursor.getString(2)));
            roupa.setRoupaData(cursor.getString(3));


            roupas.add(roupa);
        }
        return roupas;
    }

    public float getSomaRoupa() {

        String[] valor = {"valorRoupaBD"};

        Cursor cursor = getReadableDatabase().rawQuery("SELECT sum(valorRoupaBD) From RoupaTB ;",null);



        while (cursor.moveToNext()) {

            this.soma = cursor.getFloat(0);

            cursor.close();

        }
        return soma ;
    }

    public float SomaAtual(){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT sum(valorRoupaBD) From RoupaTB where dataRoupaBD Between '2016-11-01' and '2016-11-30';",null);
        while (cursor.moveToNext()){
            this.somaAtual = cursor.getFloat(0);
            cursor.close();
        }
        return somaAtual;
    }





    public void deletar(Roupa roupa ){

        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From RoupaTB ;";
        db.execSQL(sql);

    }
    public float getSomaAnterior(){

        Cursor cursor = getReadableDatabase().rawQuery("SELECT sum(valorRoupaBD) From RoupaTB where dataRoupaBD Between '2016-10-01' and '2016-10-31';",null);
        while (cursor.moveToNext()){
            this.somaAnterior = cursor.getFloat(0);

            cursor.close();

        }
        return somaAnterior;
    }
}



