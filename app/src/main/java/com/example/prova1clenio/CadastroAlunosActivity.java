package com.example.prova1clenio;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CadastroAlunosActivity extends AppCompatActivity {

    EditText editTextNome;
    EditText editTextEmail;
    Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_alunos);

        ImageView voltarButton = findViewById(R.id.voltar);
        voltarButton.setOnClickListener(view -> navigateToActivity(ActivityInicial.class));

        editTextNome = findViewById(R.id.etNome);
        editTextEmail = findViewById(R.id.etEmail);
        buttonSalvar = findViewById(R.id.btnCadastrar);

        UserRepository userRepository = new UserRepository(this);
        buttonSalvar.setOnClickListener(view -> {
            String nome = editTextNome.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            try {
                userRepository.addUser(new User(nome, "senha", email, 0, 1));
                Toast.makeText(CadastroAlunosActivity.this,
                        "Sucesso ao cadastrar usuário", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), ActivityInicial.class);
                startActivity(intent);
            } catch (Exception ex) {
                Toast.makeText(CadastroAlunosActivity.this,
                        "Erro ao cadastrar usuário", Toast.LENGTH_SHORT).show();
                ex.printStackTrace();
            }
        });

        editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && s.toString().endsWith("@")) {
                    String currentText = s.toString();
                    String newText = currentText + "restaurorapido.com";
                    editTextEmail.setText(newText);
                    editTextEmail.setSelection(newText.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void navigateToActivity(Class<?> targetActivity) {
        Intent intent = new Intent(this, targetActivity);
        startActivity(intent);
    }
}
