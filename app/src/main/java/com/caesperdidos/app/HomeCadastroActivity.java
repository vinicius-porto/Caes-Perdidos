package com.caesperdidos.app;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.AppCompatEditText;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.FirebaseDatabase;

public class HomeCadastroActivity extends AppCompatActivity {


    private MaterialButton btnCadastro;
    private FirebaseDatabase database;
    private AppCompatEditText editNome,editRaca,editCor,editDescricao;

    private static final String ERRO_NOME = "Por favor insira o nome";
    private static final String ERRO_RACA = "Por favor insira a raça";
    private static final String ERRO_COR = "Por favor insira a cor";

    private static final String ERRO_DESCRICAO = "Por favor insira a descrição";

    private void selectElements(){
        btnCadastro = findViewById(R.id.btnCadastro);
        editNome = findViewById(R.id.editNome);
        editRaca = findViewById(R.id.editRaca);
        editCor = findViewById(R.id.editCor);
        editDescricao = findViewById(R.id.editDescricao);
    }



    private  boolean verificaCampos() {
        String nome = editNome.getText().toString().trim();
        String raca = editRaca.getText().toString().trim();
        String cor = editCor.getText().toString().trim();
        String descricao = editDescricao.getText().toString().trim();

        //Verifica se os campos obrigatórios estão preenchidos
        if (nome.isEmpty()) {
            editNome.setError(ERRO_NOME);
            editNome.requestFocus();
            return false;
        } else if (raca.isEmpty()) {
            editRaca.setError(ERRO_RACA);
            editRaca.requestFocus();
            return false;
        } else if (cor.isEmpty()) {
            editCor.setError(ERRO_COR);
            editCor.requestFocus();
            return false;
        } else if (descricao.isEmpty()) {
            editDescricao.setError(ERRO_DESCRICAO);
            editDescricao.requestFocus();
            return false;
        }

        // Cria objeto
        CadastrarCachorro cadastrarCachorro = new CadastrarCachorro();
        cadastrarCachorro.setNome(nome);
        cadastrarCachorro.setRaca(raca);
        cadastrarCachorro.setCor(cor);
        cadastrarCachorro.setDescricao(descricao);
        String id = database.getReference()
                .child("cachorros")
                .push()
                .getKey();


        database.getReference()
                .child("cachorros")
                .child(id)
                .setValue(cadastrarCachorro)
                .addOnSuccessListener(unused -> {

                    Toast.makeText(this,
                            "Cachorro cadastrado com sucesso!",
                            Toast.LENGTH_LONG).show();

                    limparCampos();

                }).addOnFailureListener(e -> {

                    Toast.makeText(this,
                            "Erro ao cadastrar",
                            Toast.LENGTH_LONG).show();

                });

        return true;
    }

    private void limparCampos(){
            editNome.setText("");
            editRaca.setText("");
            editCor.setText("");
            editDescricao.setText("");
            editNome.requestFocus();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_cadastro);
        database = FirebaseDatabase.getInstance();

        selectElements();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        btnCadastro.setOnClickListener(v -> {

            verificaCampos();
        });





    }
}