package igor.com.br.money.entidades;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

import igor.com.br.money.R;
import igor.com.br.money.dao.ContaDAO;
import igor.com.br.money.dao.EntreterimentoDAO;
import igor.com.br.money.dao.RefeicaoDAO;
import igor.com.br.money.dao.RoupaDAO;

public class GraficoAnterior extends AppCompatActivity {



    public float roupa = RoupaDAO.somaAnterior;
    public float entretenimento = EntreterimentoDAO.somaAnterior;
    public float refeicao = RefeicaoDAO.somaAnterior;
    public float conta = ContaDAO.somaAnterior;
    private static String TAG = "MainActivity";
    private float[] yData = {roupa, entretenimento,refeicao,conta};
    PieChart pieChart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grafico);



        Log.d(TAG, "onCreate: starting to create chart");

        pieChart = (PieChart) findViewById(R.id.idPieChart);

        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("MyMoney");
        pieChart.setCenterTextSize(15);


        addDataSet();

    }
    private void addDataSet() {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();


        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i] , i));
        }


        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Cinza Vestuario, Azul Entretenimento, Verde Refeições, Amarelo Residencial ");
        pieDataSet.setSliceSpace(4);
        pieDataSet.setValueTextSize(20);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.LTGRAY);
        colors.add(Color.CYAN);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);


        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }




    public void onBackPressed()
    {
        Intent mudarTela = new
                Intent(GraficoAnterior.this,Main_inicio.class);
        GraficoAnterior.this.startActivity(mudarTela);
        GraficoAnterior.this.finish();
    }

}
