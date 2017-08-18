package igor.com.br.money.helper;

import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import igor.com.br.money.R;
import igor.com.br.money.controller.ContaController;
import igor.com.br.money.controller.RefeicaoController;
import igor.com.br.money.entidades.Conta;
import igor.com.br.money.entidades.Refeicao;

public class CadastrarHelperConta {

    private EditText editNome;
    private EditText editValor;
    private DatePicker datePicker;

    public CadastrarHelperConta(ContaController cadastrar){
        editNome = (EditText)  cadastrar.findViewById(R.id.editNomeConta);
        editValor = (EditText) cadastrar.findViewById(R.id.editValorConta);
        datePicker = (DatePicker) cadastrar.findViewById(R.id.datePickerConta);

    }


    public Conta PegaItensCadastro(){
        Conta conta = new Conta();
        conta.setContaNome(editNome.getText().toString());
        conta.setContaValor(Float.parseFloat(editValor.getText().toString()));

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,datePicker.getYear());
        c.set(Calendar.MONTH,datePicker.getMonth());
        c.set(Calendar.DAY_OF_MONTH,datePicker.getDayOfMonth());
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String data = s.format(c.getTime());
        conta.setContaData(data);



        return conta;
    }


}
