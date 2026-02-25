package com.caesperdidos.app;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.activity.EdgeToEdge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

  private MaterialButton btnInfo;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        //Configura o botão para exibir a mensagem sobre o app
        openMessageInfo();




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}





