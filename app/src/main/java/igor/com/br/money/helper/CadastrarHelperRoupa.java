package igor.com.br.money.helper;



import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import igor.com.br.money.R;
import igor.com.br.money.controller.RoupaController;
import igor.com.br.money.entidades.Roupa;

import static java.text.DateFormat.SHORT;
import static java.text.SimpleDateFormat.*;


public class CadastrarHelperRoupa extends AppCompatActivity {

    private EditText editNomeRoupa;
    private EditText editValorRoupa;
    private Button buscaPicker;
    private DatePicker datePicker;


    public CadastrarHelperRoupa (RoupaController roupaController){
        editNomeRoupa = (EditText)  roupaController.findViewById(R.id.editNomeRoupa);
        editValorRoupa = (EditText) roupaController.findViewById(R.id.editValorRoupa);
        datePicker = (DatePicker) roupaController.findViewById(R.id.datePicker);



    }

    public Roupa PegaDados() {


        Roupa roupa = new Roupa();
        roupa.setRoupaNome(editNomeRoupa.getText().toString());
        roupa.setRoupaValor(Float.parseFloat(editValorRoupa.getText().toString()));
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,datePicker.getYear());
        c.set(Calendar.MONTH,datePicker.getMonth());
        c.set(Calendar.DAY_OF_MONTH,datePicker.getDayOfMonth());
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

        /*
        try {
            Date d = s.parse("09-11-2016");
            Log.i("DataTeste", d.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        */
        String data = s.format(c.getTime());
        roupa.setRoupaData(data);

        return roupa;
    }






}
