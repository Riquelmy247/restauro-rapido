package com.example.prova1clenio;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityInicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        ImageView cadastro = findViewById(R.id.cadastro);
        cadastro.setOnClickListener(view -> navigateToActivity(CadastroAlunosActivity.class));

        ImageView presente = findViewById(R.id.presente);
        presente.setOnClickListener(view -> navigateToActivity(UserPresenceListActivity.class));

        ImageView lista = findViewById(R.id.lista);
        lista.setOnClickListener(view -> navigateToActivity(UserListActivity.class));

        ImageView semente = findViewById(R.id.semente);
        semente.setOnClickListener(view -> navigateToActivity(UserListEnviarActivity.class));

        ImageView feedback = findViewById(R.id.feedback);
        feedback.setOnClickListener(view -> showDevelopmentDialog());

        ImageView envio = findViewById(R.id.envio);
        envio.setOnClickListener(view -> showDevelopmentDialog());

        ImageView local = findViewById(R.id.local);
        local.setOnClickListener(view -> showDevelopmentDialog());

        ImageView relatorio = findViewById(R.id.relatorio);
        relatorio.setOnClickListener(view -> showDevelopmentDialog());

        ImageView sobre = findViewById(R.id.sobre);
        sobre.setOnClickListener(view -> navigateToActivity(SobreActivity.class));

        ImageView atualizar = findViewById(R.id.atualizar);
        atualizar.setOnClickListener(view -> showDevelopmentDialog());
    }

    private void showDevelopmentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Em Desenvolvimento")
                .setMessage("Esta funcionalidade está em desenvolvimento!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void navigateToActivity(Class<?> targetActivity) {
        Intent intent = new Intent(this, targetActivity);
        startActivity(intent);
    }
}
