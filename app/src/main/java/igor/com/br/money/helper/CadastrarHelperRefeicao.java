package igor.com.br.money.helper;

import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import igor.com.br.money.R;
import igor.com.br.money.controller.RefeicaoController;
import igor.com.br.money.entidades.Refeicao;

public class CadastrarHelperRefeicao {

    private EditText editNome;
    private EditText editValor;
    private DatePicker datePicker;

    public CadastrarHelperRefeicao(RefeicaoController cadastrar){
        editNome = (EditText)  cadastrar.findViewById(R.id.editNome);
        editValor = (EditText) cadastrar.findViewById(R.id.editValor);
        datePicker = (DatePicker) cadastrar.findViewById(R.id.datePickerRefeicao);

    }


    public Refeicao PegaItensCadastro(){
        Refeicao refeicao = new Refeicao();
        refeicao.setRefeicaoNome(editNome.getText().toString());
        refeicao.setRefeicaoValor(Float.parseFloat(editValor.getText().toString()));

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,datePicker.getYear());
        c.set(Calendar.MONTH,datePicker.getMonth());
        c.set(Calendar.DAY_OF_MONTH,datePicker.getDayOfMonth());
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String data = s.format(c.getTime());
        refeicao.setRefeicaoData(data);



        return refeicao;
    }


}
