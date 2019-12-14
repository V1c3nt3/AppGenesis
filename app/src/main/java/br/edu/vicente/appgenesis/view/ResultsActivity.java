package br.edu.vicente.appgenesis.view;

import androidx.appcompat.app.AppCompatActivity;
import br.edu.vicente.appgenesis.R;
import br.edu.vicente.appgenesis.dao.PadraoDAO;
import br.edu.vicente.appgenesis.model.Padrao;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ResultsActivity extends AppCompatActivity {
    //criação dos obj de classe
    private EditText volt;
    private EditText amper;
    private EditText metro;
    private EditText fio;
    private EditText fioRede;
    private RadioButton fioCobre;
    private RadioButton fioAlu;
    private EditText campoTensao;
    private EditText campoCorrente;
    private EditText campoDistancia;
    private EditText campoPotencia;
    private EditText campoDisjuntor;
    private TextView tipoDisjuntor;
    private EditText campoQueda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        setTitle("RESULTADO FINAL");
        instanciar();
        final double cobre = 0.0172;
        final double alu = 0.0282;

        double e = Double.parseDouble(volt.getText().toString());
        double a = Double.parseDouble(amper.getText().toString());
        double m = Double.parseDouble(metro.getText().toString());
        double f = Double.parseDouble(fioRede.getText().toString());

        //varaiveis locais para metodos
        double resistencia = 0;
        double queda = 0;
        final double fator = 0.92;
        double porcento = 0;
        //faz uma comparação se todos os parametros foram inseridos
        if (e > 0 && a > 0 && m > 0 && f > 0){
            /*seleciona o tipo de material pois o resultado varia de acordo com
             a resistividade do material*/
            if (fioCobre.isChecked()){
                resistencia = cobre * m / f;
                if (e > 0){
                    queda = (2*resistencia)*a*fator;
                    porcento = 100* (queda / e);
                    campoTensao.setText(String.valueOf(e));
                    campoCorrente.setText(String.valueOf(a));
                    campoDistancia.setText(String.valueOf(m));
                    fio.setText(String.valueOf(f));
                    campoQueda.setText(String.valueOf(String.format("%.2f",porcento)));
                    campoPotencia.setText(String.valueOf(String.format("%.2f",(e*a))));
                    double p =Double.parseDouble(campoPotencia.getText().toString());
                    //resultados variam de acordo com a tensão de entrada
                    if (e < 150) {
                        campoDisjuntor.setText(String.valueOf(String.format("%.1f", p * 0.0095)));
                        tipoDisjuntor.setText("MONOFÁSICO");
                    }else if (e < 380){
                        campoDisjuntor.setText(String.valueOf(String.format("%.1f", p * 0.0055)));
                        tipoDisjuntor.setText("BIFÁSICO");
                    }else
                        Toast.makeText(this,"Recalcular o circuito de proteção",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,"Informe valor maior que zero",Toast.LENGTH_LONG).show();
                }
            }else if(fioAlu.isChecked()){
                 resistencia = alu * m / f;
                if (e > 0){
                    queda = (2*resistencia)*a*fator;
                    porcento = 100* (queda / e);
                    campoTensao.setText(String.valueOf(e));
                    campoCorrente.setText(String.valueOf(a));
                    campoDistancia.setText(String.valueOf(m));
                    fio.setText(String.valueOf(f));
                    campoQueda.setText(String.valueOf(String.format("%.2f",porcento)));
                    campoPotencia.setText(String.valueOf(String.format("%.2f",(e*a))));
                    double p =Double.parseDouble(campoPotencia.getText().toString());
                    if (e < 150) {
                        campoDisjuntor.setText(String.valueOf(String.format("%.1f", p * 0.0095)));
                        tipoDisjuntor.setText("MONOFÁSICO");
                    }else if (e < 380){
                        campoDisjuntor.setText(String.valueOf(String.format("%.1f", p * 0.0055)));
                        tipoDisjuntor.setText("BIFÁSICO");
                    }else
                        Toast.makeText(this,"Recalcular o circuito de proteção",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,"Informe valor maior que zero",Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this,"Nenhum material selecionado", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Informe todos os valores", Toast.LENGTH_LONG).show();
        }
    }

    private void instanciar(){
        campoTensao = (EditText)findViewById(R.id.edt_tensaoRes);
        campoCorrente = (EditText)findViewById(R.id.edt_correnteRes);
        campoDistancia = (EditText)findViewById(R.id.edt_distanciaRes);
        campoPotencia = (EditText)findViewById(R.id.edt_potenciaRes);
        fio = (EditText) findViewById(R.id.edt_bitolaRes);
        campoDisjuntor = (EditText)findViewById(R.id.edt_disjRes);
        tipoDisjuntor = (TextView) findViewById(R.id.edt_tipoDisj);
        campoQueda = (EditText)findViewById(R.id.edt_queda);
        volt = (EditText) findViewById(R.id.edt_tensao);
        amper = (EditText)findViewById(R.id.edt_corrente);
        metro = (EditText)findViewById(R.id.edt_distancia);
        fioRede = (EditText) findViewById(R.id.edt_bitola);
        fioCobre = (RadioButton) findViewById(R.id.rad_cobre);
        fioAlu = (RadioButton) findViewById(R.id.rad_alum);

    }

}
