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
public class HomeCadastroActivity extends AppCompatActivity {


    private MaterialButton btnCadastro;
    private AppCompatEditText editNome,editRaca,editCor,editDescricao;


    private void selectElements(){
        btnCadastro = findViewById(R.id.btnCadastro);
        editNome = findViewById(R.id.editNome);
        editRaca = findViewById(R.id.editRaca);
        editCor = findViewById(R.id.editCor);
        editDescricao = findViewById(R.id.editDescricao);
    }

    private final String[] mensagensErro = {
            "Por favor, insira o nome",
            "Por favor, insira a raça",
            "Por favor, insira a cor",
            "Por favor, insira a descrição"
    };


    private void verificaCampos() {
        String nome = editNome.getText().toString().trim();
        String raca = editRaca.getText().toString().trim();
        String cor = editCor.getText().toString().trim();
        String descricao = editDescricao.getText().toString().trim();

        //Verifica se os campos obrigatórios estão preenchidos
        if (nome.isEmpty()) {
            editNome.setError(mensagensErro[0]);
            editNome.requestFocus();
            return;
        }

        if (raca.isEmpty()) {
            editRaca.setError(mensagensErro[1]);
            editRaca.requestFocus();
            return;
        }

        if (cor.isEmpty()) {
            editCor.setError(mensagensErro[2]);
            editCor.requestFocus();
            return;
        }

        if (descricao.isEmpty()) {
            editDescricao.setError(mensagensErro[3]);
            editDescricao.requestFocus();
            return;
        }

        // Cria objeto
        CadastrarCachorro cadastrarCachorro = new CadastrarCachorro();
        cadastrarCachorro.setNome(nome);
        cadastrarCachorro.setRaca(raca);
        cadastrarCachorro.setCor(cor);
        cadastrarCachorro.setDescricao(descricao);


        Toast.makeText(this,
                "Cachorro cadastrado:\n" +
                        "Nome: " + cadastrarCachorro.getNome() + "\n" +
                        "Raça: " + cadastrarCachorro.getRaca() + "\n" +
                        "Cor: " + cadastrarCachorro.getCor() + "\n" +
                        "Descrição: " + cadastrarCachorro.getDescricao(),
                Toast.LENGTH_LONG
        ).show();


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



        selectElements();



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        btnCadastro.setOnClickListener(v -> {


            verificaCampos();

            limparCampos();





        });





    }
}