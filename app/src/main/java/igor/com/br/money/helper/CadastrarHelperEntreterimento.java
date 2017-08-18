package igor.com.br.money.helper;



import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import igor.com.br.money.R;
import igor.com.br.money.controller.EntreterimentoController;
import igor.com.br.money.controller.RoupaController;
import igor.com.br.money.entidades.Entreterimento;
import igor.com.br.money.entidades.Roupa;


public class CadastrarHelperEntreterimento extends AppCompatActivity {

    private EditText editNomeEntre;
    private EditText editValorEntre;

    private DatePicker datePicker;


    public CadastrarHelperEntreterimento(EntreterimentoController entreterimentoController){

        editNomeEntre = (EditText)  entreterimentoController.findViewById(R.id.editNomeEntre);
        editValorEntre = (EditText) entreterimentoController.findViewById(R.id.editValorEntre);
        datePicker = (DatePicker) entreterimentoController.findViewById(R.id.datePickerEntre);



    }

    public Entreterimento PegaDadosEntre() {



        Entreterimento entre = new Entreterimento();

        entre.setNomeEntre(editNomeEntre.getText().toString());
        entre.setValorEntre(Float.parseFloat(editValorEntre.getText().toString()));

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,datePicker.getYear());
        c.set(Calendar.MONTH,datePicker.getMonth());
        c.set(Calendar.DAY_OF_MONTH,datePicker.getDayOfMonth());
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String data = s.format(c.getTime());
        entre.setDataEntre(data);

        return entre;
    }






}
