package br.edu.vicente.appgenesis.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.vicente.appgenesis.model.Padrao;

public class PadraoDAO {
    //implementação de obj da classe conexão e do SQLite
    private Conexao conexao;
    private SQLiteDatabase banco;

    public PadraoDAO(Context context) {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    //metodo de inserir daddos na tabela do banco de dados
    public long inserirDados(Padrao padrao){
        //cria obj para receber os valores esalvar no bd
        ContentValues values = new ContentValues();
        values.put("tensao", padrao.getTensao());
        values.put("corrente", padrao.getCorrente());
        values.put("distancia",padrao.getDistancia());
        values.put("potencia",padrao.getPotencia());
        values.put("disjuntor",padrao.getDisjuntor());
        values.put("queda",padrao.getQueda());
        return banco.insert("calculo", null, values);
    }

    //metodo read do crud para listagem dos itens do banco
    public List<Padrao> obterDados(){
        //cria um obj do tipo List
        List<Padrao> dados = new ArrayList<>();
        Cursor cursor = banco.query(
                "calculo",
                new String[]{"id","tensao","corrente","distancia","potencia", "disjuntor","queda"},
                null,
                null,
                null,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            Padrao padrao = new Padrao();
            //instancia os atributos para o obj da classe principal
            padrao.setId(cursor.getInt(0));
            padrao.setTensao(cursor.getString(1));
            padrao.setCorrente(cursor.getString(2));
            padrao.setDistancia(cursor.getString(3));
            padrao.setPotencia(cursor.getString(4));
            padrao.setDisjuntor(cursor.getString(5));
            padrao.setQueda(cursor.getString(6));

            //insere na lista os objetos inseridos
            dados.add(padrao);
        }
        return dados;
    }

    //metodo para excluir os dados inseridos na tabela
    public void excluirDados(Padrao padrao){
        banco.delete("calculo","id==?",
                new String[]{String.valueOf(padrao.getId())});
    }

    public void atualizarDados(Padrao padrao){
        ContentValues values = new ContentValues();
        //busca os valores e insere novos valores
        values.put("tensao", padrao.getTensao());
        values.put("corrente", padrao.getCorrente());
        values.put("distancia", padrao.getDistancia());
        values.put("potencia", padrao.getPotencia());
        values.put("disjuntor", padrao.getDisjuntor());
        values.put("queda", padrao.getQueda());

        //update nos dados após edição
        banco.update("calculo", values, "id = ?",
                new String[]{String.valueOf(padrao.getId())});
    }
}
