package com.example.prova1clenio;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView voltarButton = findViewById(R.id.voltar);
        voltarButton.setOnClickListener(view -> navigateToActivity(ActivityInicial.class));

        ImageView cerradoImage = findViewById(R.id.cerradoImage);
        TextView textoSobre = findViewById(R.id.textoSobre);
    }

    private void navigateToActivity(Class<?> targetActivity) {
        Intent intent = new Intent(this, targetActivity);
        startActivity(intent);
    }
}
