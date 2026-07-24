package com.caesperdidos.app;
import android.app.AlertDialog;
import android.widget.EditText;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;
import com.google.firebase.database.FirebaseDatabase;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CachorroAdapter extends ArrayAdapter<CadastrarCachorro> {

        public CachorroAdapter(Activity activity, List<CadastrarCachorro> lista) {
            super(activity, 0, lista);
        }


        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.item_cachorro, parent, false);
            }

            TextView txtNome = convertView.findViewById(R.id.txtNome);
            TextView txtRaca = convertView.findViewById(R.id.txtRaca);
            TextView txtCor = convertView.findViewById(R.id.txtCor);
            TextView txtDescricao  = convertView.findViewById(R.id.txtDescricao);
            TextView txtTelefone  = convertView.findViewById(R.id.txtTelefone);
            TextView txtTutor   = convertView.findViewById(R.id.txtTutor);
            TextView txtLocalizacao = convertView.findViewById(R.id.txtLocalizacao);
            Button btnExcluir = convertView.findViewById(R.id.btnExcluir);
            Button btnEditar = convertView.findViewById(R.id.btnEditar);


            CadastrarCachorro cachorro = getItem(position);

            btnExcluir.setOnClickListener(v -> {

                FirebaseDatabase.getInstance()
                        .getReference("cachorros")
                        .child(cachorro.getId())
                        .removeValue();

            });


            btnEditar.setOnClickListener(v -> {

                View view = LayoutInflater.from(getContext())
                        .inflate(R.layout.dialog_editar_cachorro, null);

                EditText edtNome = view.findViewById(R.id.edtNome);
                EditText edtRaca = view.findViewById(R.id.edtRaca);
                EditText edtCor = view.findViewById(R.id.edtCor);
                EditText edtDescricao = view.findViewById(R.id.edtDescricao);
                EditText edtTelefone = view.findViewById(R.id.edtTelefone);
                EditText edtTutor = view.findViewById(R.id.edtTutor);
                EditText edtLocalizacao = view.findViewById(R.id.edtLocalizacao);

                // Preenche os campos
                edtNome.setText(cachorro.getNome());
                edtRaca.setText(cachorro.getRaca());
                edtCor.setText(cachorro.getCor());
                edtDescricao.setText(cachorro.getDescricao());
                edtTelefone.setText(cachorro.getTelefone());
                edtTutor.setText(cachorro.getTutor());
                edtLocalizacao.setText(cachorro.getLocalizacao());

                new AlertDialog.Builder(getContext())
                        .setTitle("Editar Cachorro")
                        .setView(view)
                        .setPositiveButton("Salvar", (dialog, which) -> {

                            cachorro.setNome(edtNome.getText().toString());
                            cachorro.setRaca(edtRaca.getText().toString());
                            cachorro.setCor(edtCor.getText().toString());
                            cachorro.setDescricao(edtDescricao.getText().toString());
                            cachorro.setTelefone(edtTelefone.getText().toString());
                            cachorro.setTutor(edtTutor.getText().toString());
                            cachorro.setLocalizacao(edtLocalizacao.getText().toString());

                            FirebaseDatabase.getInstance()
                                    .getReference("cachorros")
                                    .child(cachorro.getId())
                                    .setValue(cachorro);

                            notifyDataSetChanged();

                        })
                        .setNegativeButton("Cancelar", null)
                        .show();

            });

            if (cachorro != null) {
                txtNome.setText("🐶Nome: " + cachorro.getNome());
                txtRaca.setText("🐾Raça: " + cachorro.getRaca());
                txtCor.setText("🎨Cor: " + cachorro.getCor());
                txtDescricao.setText("📄Descrição: " + cachorro.getDescricao());
                txtTelefone.setText("📞Telefone: " + cachorro.getTelefone());
                txtTutor.setText("👤Tutor: " + cachorro.getTutor());
                txtLocalizacao.setText("📍Localizacão: " + cachorro.getLocalizacao());


            }

            return convertView;
        }
    }


