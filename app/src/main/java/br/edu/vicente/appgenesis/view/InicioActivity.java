package br.edu.vicente.appgenesis.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import br.edu.vicente.appgenesis.R;
import br.edu.vicente.appgenesis.dao.PadraoDAO;
import br.edu.vicente.appgenesis.model.Padrao;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

import static androidx.core.os.LocaleListCompat.create;

public class InicioActivity extends AppCompatActivity {
    //obj para comunicar com as outras classes
    ListView listaDados;
    PadraoDAO padraoDAO;
    List<Padrao> dados;

    private EditText voltagem;
    private EditText amperagem;
    private EditText metrica;
    private EditText diametroFio;
    private RadioButton cu;
    private RadioButton al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        setTitle("DADOS DA REDE");
        listaDados = (ListView) findViewById(R.id.lista_rede);

        //instanciar um obj da classe DAO
        padraoDAO = new PadraoDAO(this);
        //traz os dados armazenados para a tela pricipal
        dados = padraoDAO.obterDados();

        ArrayAdapter adapter = new ArrayAdapter<Padrao>(
                this, android.R.layout.simple_list_item_1,
                dados);

        listaDados.setAdapter(adapter);
        registerForContextMenu(listaDados);
    }

    public void calcularDados(View view) {
        instanciarCampo();

        if (voltagem != null && amperagem != null && metrica != null && diametroFio != null && (cu.isChecked()||al.isChecked())){
            //chama a outra tela para exibir os resultados
            startActivity(new Intent(InicioActivity.this, ResultsActivity.class));
        }else{
            Toast.makeText(this, "Informe todos os valores", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        dados = padraoDAO.obterDados();
        ArrayAdapter adapter = new ArrayAdapter<Padrao>(
                this, android.R.layout.simple_list_item_1,
                dados);
        listaDados.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_contexto, menu);
    }

    public void excluirDados(MenuItem item){
        //criar um adaptar view para ver qual item foi clicado
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        //transformar num obj do tipo tarefa o item clicado
        final Padrao excluirPadrao = dados.get(menuInfo.position);

        //criar uma caixa de dialogo para confirmar a exclusão
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção!")
                .setMessage("DESEJA REALMENTE EXCLUIR O CADASTRO")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //remover da ListView ou lista de tarefas
                        dados.remove(excluirPadrao);
                        //remover do BD
                        padraoDAO.excluirDados(excluirPadrao);
                        //atualizar a listView, reovendo o item selecionado para organizar
                        listaDados.invalidateViews();
                    }
                }).create();
        //mostrar a caixa de dialogo ao usuario
        dialog.show();
    }

    public void instanciarCampo(){
        voltagem = (EditText) findViewById(R.id.edt_tensao);
        amperagem = (EditText) findViewById(R.id.edt_corrente);
        metrica = (EditText) findViewById(R.id.edt_distancia);
        diametroFio = (EditText) findViewById(R.id.edt_bitola);
        cu = (RadioButton) findViewById(R.id.rad_cobre);
        al = (RadioButton) findViewById(R.id.rad_alum);
    }
}
