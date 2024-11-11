package com.example.prova1clenio;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserListEnviarActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserEnviarAdapter userAdapter;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_enviar_list);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView voltarButton = findViewById(R.id.voltar);
        voltarButton.setOnClickListener(view -> navigateToActivity(ActivityInicial.class));

        DBHelper dbHelper = new DBHelper(this);
        userRepository = new UserRepository(dbHelper);

        recyclerView = findViewById(R.id.recyclerViewUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadUsers();
    }

    private void loadUsers() {
        List<User> users = userRepository.getUsersWithPermissionAndPointsTen();

        if (userAdapter == null) {
            userAdapter = new UserEnviarAdapter(this, users, userRepository);
            recyclerView.setAdapter(userAdapter);
        } else {
            userAdapter.updateUsers(users);
        }
    }

    private void navigateToActivity(Class<?> targetActivity) {
        Intent intent = new Intent(this, targetActivity);
        startActivity(intent);
    }
}
