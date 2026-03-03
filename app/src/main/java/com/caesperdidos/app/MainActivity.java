package com.caesperdidos.app;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.activity.EdgeToEdge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

  private MaterialButton btnInfo,btnClick;

    /// Mostra informações sobre o aplicativo
  private void openMessageInfo(){
          btnInfo = findViewById(R.id.btnInfo);

      btnInfo.setOnClickListener(view -> {

          AlertDialog.Builder alertDialog = new  AlertDialog.Builder(MainActivity.this);
          alertDialog.setTitle("Sobre o aplicativo:");
          alertDialog.setMessage("Objetivo: Localizar e resgatar cães perdidos pelo mundo");
          alertDialog.show();

      });
  }

  private void openPageHome(){
      btnClick = findViewById(R.id.btnClick);
      btnClick.setOnClickListener(view -> {
          Intent intent = new Intent(MainActivity.this, HomeActivity.class);
          startActivity(intent);
      });
  }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        //Configura o botão para exibir a mensagem sobre o app
        openMessageInfo();

        //exibe a pagina home do aplicativo
        openPageHome();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}





