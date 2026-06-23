package com.caesperdidos.app;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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



            CadastrarCachorro cachorro = getItem(position);

            if (cachorro != null) {
                txtNome.setText("🐶Nome: " + cachorro.getNome());
                txtRaca.setText("🐾Raça: " + cachorro.getRaca());
                txtCor.setText("🎨Cor: " + cachorro.getCor());
                txtDescricao.setText("📄Descrição: " + cachorro.getDescricao());
                txtTelefone.setText("📞Telefone: " + cachorro.getTelefone());
                txtTutor.setText("👤Tutor: " + cachorro.getTutor());



            }

            return convertView;
        }
    }


