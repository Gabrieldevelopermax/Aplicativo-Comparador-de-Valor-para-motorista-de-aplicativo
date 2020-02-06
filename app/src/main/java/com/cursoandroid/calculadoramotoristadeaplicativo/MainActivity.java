package com.cursoandroid.calculadoramotoristadeaplicativo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private Button botaoEnviar;
    private TextView textoResultado;
    private TextView textoKilometragem;
    private TextView textoTempo;
    private SeekBar seekbar;
    private TextView textoSeekBarId;
    double dinamico;
    double resultadoSomaUber;
    double resultadoSomaNine ;



    /*
    static double ajuste(double tempoComSegundos) {
        int tempoInteiro = (int) tempoComSegundos;
        double segundos = tempoComSegundos - tempoInteiro;
        double resultTempo = ((tempoInteiro + (segundos * 1.6))*0.1950);
        return resultTempo;
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        botaoEnviar = findViewById(R.id.enviar);
        textoResultado = findViewById(R.id.resultado);
        textoKilometragem = findViewById(R.id.kilometragem);
        textoTempo = findViewById(R.id.tempo);
        seekbar=findViewById(R.id.seekBarId);
        textoSeekBarId=findViewById(R.id.textoSeekBarId);


        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Uber

                //-------------------------------------------- COONVERSOES

                //--------------------------UBER
                //textoResultado.setText(textoKilometragem.getText());
                String convertKiloStringUber = textoKilometragem.getText().toString(); //converte em String
                double convertKiloDoubleUber = Double.parseDouble(convertKiloStringUber);//converte String em double

                String convertTempoStringUber = textoTempo.getText().toString(); //converte em String
                double convertTempoDoubleUber = Double.parseDouble(convertTempoStringUber); //converte String em double


                //------------------------  99POP
                String convertKiloStringNine = textoKilometragem.getText().toString(); //converte em string
                double convertKiloDoubleNine = Double.parseDouble(convertKiloStringNine); //converte em double

                String convertTempoStringNine = textoTempo.getText().toString(); //converte em string
                double convertTempoDoubleNine = Double.parseDouble(convertTempoStringNine); //converte em double

                //----------------------------------------------CALCULOS KM E TEMPO UBER

                //double resultTempoUber=ajuste(convertTempoDoubleUber); //// usando o ajuste de tempo
                double resultKiloUber = (convertKiloDoubleUber * 1.05);
                double resultTempoUber=convertTempoDoubleUber*0.1950;
                resultadoSomaUber = (resultKiloUber + resultTempoUber + 1.50+0.01/* correcao de 1 segundo */);


                //----------------------------------------------CALCULOS KM E TEMPO 99POP

                //double resultTempoNine = ajuste(convertTempoDoubleNine);
                double resultKiloNine = (convertKiloDoubleNine * 1.30);
                double resultTempoNine=convertTempoDoubleNine*0.10;
                resultadoSomaNine = (resultKiloNine + resultTempoNine + 1.50);

               textoResultado.setText(" A UBER $ "+ NumberFormat.getCurrencyInstance().format(resultadoSomaUber) + " " + " reais. \n " + " A 99 POP $ " + NumberFormat.getCurrencyInstance().format(resultadoSomaNine) + " reais. ");


            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 1){
                    textoSeekBarId.setText("1.0");

                }else if(progress > 1 && progress != 0){

                    dinamico=(progress-1)*0.1+1;
                    DecimalFormat df= new DecimalFormat("#0.0");
                    textoSeekBarId.setText(" "+df.format(dinamico));
                    textoResultado.setText(" A UBER $ "+ NumberFormat.getCurrencyInstance().format(resultadoSomaUber*dinamico) + " " + " reais. \n " + " A 99 POP $ " + NumberFormat.getCurrencyInstance().format(resultadoSomaNine*dinamico) + " reais. ");


                }


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}
