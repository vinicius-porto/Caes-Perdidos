package com.caesperdidos.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;
public class EditarCachorroActivity extends AppCompatActivity {

    private TextInputEditText editNome;
    private TextInputEditText editRaca;
    private TextInputEditText editCor;
    private TextInputEditText editDescricao;
    private TextInputEditText editTelefone;
    private TextInputEditText editTutor;
    private TextInputEditText editLocalizacao;

    private MaterialButton btnEditar;

    private Cachorro cachorro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_cachorro);

        editNome = findViewById(R.id.editNome);
        editRaca = findViewById(R.id.editRaca);
        editCor = findViewById(R.id.editCor);
        editDescricao = findViewById(R.id.editDescricao);
        editTelefone = findViewById(R.id.editTelefone);
        editTutor = findViewById(R.id.editTutor);
        editLocalizacao = findViewById(R.id.editLocalizacao);
        btnEditar = findViewById(R.id.btnEditar);
        cachorro = (Cachorro) getIntent()
                .getSerializableExtra("cachorro");


        if (cachorro != null) {

            editNome.setText(cachorro.getNome());
            editRaca.setText(cachorro.getRaca());
            editCor.setText(cachorro.getCor());
            editDescricao.setText(cachorro.getDescricao());
            editTelefone.setText(cachorro.getTelefone());
            editTutor.setText(cachorro.getTutor());
            editLocalizacao.setText(cachorro.getLocalizacao());
        }
        btnEditar.setOnClickListener(v -> {

            if (cachorro == null) {
                Toast.makeText(
                        this,
                        "Erro: cachorro não encontrado",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }


            cachorro.setNome(editNome.getText().toString().trim());
            cachorro.setRaca(editRaca.getText().toString().trim());
            cachorro.setCor(editCor.getText().toString().trim());
            cachorro.setDescricao(editDescricao.getText().toString().trim());
            cachorro.setTelefone(editTelefone.getText().toString().trim());
            cachorro.setTutor(editTutor.getText().toString().trim());
            cachorro.setLocalizacao(editLocalizacao.getText().toString().trim());


            FirebaseDatabase.getInstance()
                    .getReference("cachorros")
                    .child(cachorro.getId())
                    .setValue(cachorro)
                    .addOnSuccessListener(unused -> {

                        Toast.makeText(
                                this,
                                "Cachorro atualizado!",
                                Toast.LENGTH_SHORT
                        ).show();

                        finish();
                    });

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}