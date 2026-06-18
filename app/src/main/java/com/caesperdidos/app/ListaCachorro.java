package com.caesperdidos.app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

import java.util.ArrayList;


public class ListaCachorro extends AppCompatActivity {

    private FirebaseDatabase database;
    private ListView listView;
    private ArrayList<CadastrarCachorro> lista;
    private CachorroAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_cachorro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView = findViewById(R.id.listViewCachorros);
        database = FirebaseDatabase.getInstance();
        lista = new ArrayList<>();

        adapter = new CachorroAdapter(this, lista);

        listView.setAdapter(adapter);
        carregarDados();




    }

    private void carregarDados() {

        database.getReference("cachorros")
                .addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        atualizarLista(snapshot);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
    }

    private void atualizarLista(DataSnapshot snapshot) {

        lista.clear();

        for (DataSnapshot dados : snapshot.getChildren()) {

            CadastrarCachorro cachorro = dados.getValue(CadastrarCachorro.class);

            if (cachorro != null) {
                lista.add(cachorro);
            }
        }

        adapter.notifyDataSetChanged();
    }




}