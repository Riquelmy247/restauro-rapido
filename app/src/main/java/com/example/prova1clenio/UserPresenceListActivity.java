package com.example.prova1clenio;

import static com.example.prova1clenio.DBHelper.COLUMN_POINTS;
import static com.example.prova1clenio.DBHelper.COLUMN_NAME;
import static com.example.prova1clenio.DBHelper.TABLE_USERS;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserPresenceListActivity extends AppCompatActivity {
    private UserRepository userRepository;
    private RecyclerView presenceRecyclerView;
    private PresenceAdapter presenceAdapter;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_presence_list);

        dbHelper = new DBHelper(this);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView voltarButton = findViewById(R.id.voltar);
        voltarButton.setOnClickListener(view -> navigateToActivity(ActivityInicial.class));

        presenceRecyclerView = findViewById(R.id.presenceRecyclerView);
        presenceRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        userRepository = new UserRepository(this);
        List<User> userList = userRepository.getUsersWithPermission();

        presenceAdapter = new PresenceAdapter(userList);
        presenceRecyclerView.setAdapter(presenceAdapter);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v -> {
            for (User user : userList) {
                savePresenceStatus(user.getNome(), user.isPresent());
            }
            navigateToActivity(ActivityInicial.class);
        });
    }

    public void savePresenceStatus(String userName, boolean isPresent) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (isPresent) {
            db.execSQL("UPDATE " + TABLE_USERS +
                            " SET " + COLUMN_POINTS + " = " + COLUMN_POINTS + " + 1 WHERE " + COLUMN_NAME + " = ?",
                    new String[]{userName});
        }

        db.close();
    }

    private void navigateToActivity(Class<?> targetActivity) {
        Intent intent = new Intent(this, targetActivity);
        startActivity(intent);
    }
}
