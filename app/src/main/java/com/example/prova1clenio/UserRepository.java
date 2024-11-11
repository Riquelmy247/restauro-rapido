package com.example.prova1clenio;

import static com.example.prova1clenio.DBHelper.COLUMN_EMAIL;
import static com.example.prova1clenio.DBHelper.COLUMN_ID;
import static com.example.prova1clenio.DBHelper.COLUMN_PASSWORD;
import static com.example.prova1clenio.DBHelper.TABLE_USERS;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepository {
    private static final String TAG = "UserRepository";
    private DBHelper dbHelper;

    public UserRepository(Context context) {
        dbHelper = new DBHelper(context);
    }

    public UserRepository(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", user.getNome());
        values.put("email", user.getEmail());
        values.put("senha", user.getSenha());
        values.put("pontos", user.getPontos());
        values.put("permissao", user.getPermissao());

        long result = db.insert("users", null, values);
        if (result == -1) {
            throw new SQLiteConstraintException("Erro ao inserir usuário.");
        }
        db.close();
    }

    public boolean checkUser(String email, String senha) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT " + DBHelper.COLUMN_ID + " FROM " + DBHelper.TABLE_USERS +
                    " WHERE " + DBHelper.COLUMN_EMAIL + " = ? AND " + DBHelper.COLUMN_PASSWORD + " = ?;";

            cursor = db.rawQuery(query, new String[]{email, senha});

            return cursor != null && cursor.moveToFirst();
        } catch (Exception e) {
            Log.e("UserRepository", "Error checking user: ", e);
            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    public List<User> getUsersWithPermission() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT " + DBHelper.COLUMN_NAME + ", " + DBHelper.COLUMN_POINTS +
                " FROM " + TABLE_USERS +
                " WHERE " + DBHelper.COLUMN_PERMISSAO + " = 1" +
                " ORDER BY " + DBHelper.COLUMN_NAME + " ASC;";

        Cursor cursor = null;

        try {
            cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range")
                    String name = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME));
                    @SuppressLint("Range")
                    int points = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_POINTS));
                    userList.add(new User(name, points));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return userList;
    }

    public List<User> getUsersWithPermissionAndPointsTen() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT " + DBHelper.COLUMN_NAME + ", " + DBHelper.COLUMN_POINTS +
                " FROM " + DBHelper.TABLE_USERS +
                " WHERE " + DBHelper.COLUMN_PERMISSAO + " = 1" +
                " AND " + DBHelper.COLUMN_POINTS + " >= 10" +
                " ORDER BY " + DBHelper.COLUMN_NAME + " ASC;";

        Cursor cursor = null;

        try {
            cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range")
                    String name = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME));
                    @SuppressLint("Range")
                    int points = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_POINTS));
                    userList.add(new User(name, points));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return userList;
    }

    public void updateUserPoints(String userName, int newPoints) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_POINTS, newPoints);

        String selection = DBHelper.COLUMN_NAME + " = ?";
        String[] selectionArgs = { userName };

        db.update(DBHelper.TABLE_USERS, values, selection, selectionArgs);
        db.close();
    }
}
