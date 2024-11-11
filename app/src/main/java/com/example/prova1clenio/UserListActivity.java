package com.example.prova1clenio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserListActivity extends AppCompatActivity {
    private UserRepository userRepository;
    private RecyclerView userRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        userRecyclerView = findViewById(R.id.userRecyclerView);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        userRepository = new UserRepository(this);
        List<User> userList = userRepository.getUsersWithPermission();

        UserAdapter userAdapter = new UserAdapter(userList);
        userRecyclerView.setAdapter(userAdapter);

        ImageView voltarButton = findViewById(R.id.voltar);
        voltarButton.setOnClickListener(view -> navigateToActivity(ActivityInicial.class));
    }

    private void navigateToActivity(Class<?> targetActivity) {
        Intent intent = new Intent(this, targetActivity);
        startActivity(intent);
    }
}
