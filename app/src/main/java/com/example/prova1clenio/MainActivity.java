package com.example.prova1clenio;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin;
    private UserRepository userRepository;
    private DBHelper dbHelper;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.login);

        dbHelper = new DBHelper(this);
        dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 1, 2);

        userRepository = new UserRepository(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                Log.i(TAG, "Tentando login com email: " + username);

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Entre com usuário e senha", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isAuthenticated = userRepository.checkUser(username, password);
                if (isAuthenticated) {
                    Toast.makeText(MainActivity.this, "Login bem-sucedido", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ActivityInicial.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        editTextUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && s.toString().endsWith("@")) {
                    String currentText = s.toString();
                    String newText = currentText + "restaurorapido.com"; // Adiciona o sufixo sem o '@'
                    editTextUsername.setText(newText);
                    editTextUsername.setSelection(newText.length()); // Move o cursor para o final
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
