package com.example.prova1clenio;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "usuarios.db";
    private static final int DATABASE_VERSION = 2;
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "nome";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "senha";
    public static final String COLUMN_POINTS = "pontos";
    public static final String COLUMN_PERMISSAO = "permissao";

    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_USERS + "(" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME
            + " TEXT NOT NULL, " + COLUMN_EMAIL
            + " TEXT NOT NULL, " + COLUMN_PASSWORD
            + " INTEGER NOT NULL, " + COLUMN_POINTS
            + " INTEGER NOT NULL, " + COLUMN_PERMISSAO
            + " INTEGER DEFAULT 0);";

    private static final String[] INITIAL_USERS = {
            "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_POINTS + ", " + COLUMN_PERMISSAO + ") VALUES ('Sara Cristina De Oliveira Bernardes', 'sara@restaurorapido.com', 'sara123', 0, 0);",
            "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_POINTS + ", " + COLUMN_PERMISSAO + ") VALUES ('Eduarda Moraes Silva', 'eduarda@restaurorapido.com', 'eduarda123', 0, 0);",
            "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_POINTS + ", " + COLUMN_PERMISSAO + ") VALUES ('Carollayne Moreira Borges', 'carollayne@restaurorapido.com', 'carollayne123', 0, 0);",
            "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_POINTS + ", " + COLUMN_PERMISSAO + ") VALUES ('adm', 'adm@restaurorapido.com', 'adm123', 0, 0);",
            "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_POINTS + ", " + COLUMN_PERMISSAO + ") VALUES ('Lucas Almeida', 'lucas@restaurorapido.com', 'password1', 10, 1);",
            "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_POINTS + ", " + COLUMN_PERMISSAO + ") VALUES ('Maria Santos', 'maria@restaurorapido.com', 'password2', 12, 1);",
            "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_POINTS + ", " + COLUMN_PERMISSAO + ") VALUES ('Pedro Silva', 'pedro@restaurorapido.com', 'password3', 24, 1);",
            "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_POINTS + ", " + COLUMN_PERMISSAO + ") VALUES ('Juliana Pereira', 'juliana@restaurorapido.com', 'password4', 6, 1);",
            "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_POINTS + ", " + COLUMN_PERMISSAO + ") VALUES ('Roberto Costa', 'roberto@restaurorapido.com', 'password5', 16, 1);",
            "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_POINTS + ", " + COLUMN_PERMISSAO + ") VALUES ('Fernanda Oliveira', 'fernanda@restaurorapido.com', 'password6', 8, 1);",
            "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_POINTS + ", " + COLUMN_PERMISSAO + ") VALUES ('Thiago Sousa', 'thiago@restaurorapido.com', 'password7', 9, 1);",
            "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_POINTS + ", " + COLUMN_PERMISSAO + ") VALUES ('Carla Mendes', 'carla@restaurorapido.com', 'password8', 15, 1);",
            "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_POINTS + ", " + COLUMN_PERMISSAO + ") VALUES ('Ricardo Dias', 'ricardo@restaurorapido.com', 'password9', 16, 1);",
            "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_POINTS + ", " + COLUMN_PERMISSAO + ") VALUES ('Patricia Lima', 'patricia@restaurorapido.com', 'password10', 12, 1);",
            "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_POINTS + ", " + COLUMN_PERMISSAO + ") VALUES ('Bruno Rocha', 'bruno@restaurorapido.com', 'password11', 6, 1);",
            "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_POINTS + ", " + COLUMN_PERMISSAO + ") VALUES ('Ana Carvalho', 'ana@restaurorapido.com', 'password12', 8, 1);",
            "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_POINTS + ", " + COLUMN_PERMISSAO + ") VALUES ('Fábio Martins', 'fabio@restaurorapido.com', 'password13', 9, 1);",
            "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_POINTS + ", " + COLUMN_PERMISSAO + ") VALUES ('Luciana Gomes', 'luciana@restaurorapido.com', 'password14', 5, 1);",
            "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_POINTS + ", " + COLUMN_PERMISSAO + ") VALUES ('Renato Nunes', 'renato@restaurorapido.com', 'password15', 23, 1);",
            "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_POINTS + ", " + COLUMN_PERMISSAO + ") VALUES ('Isabela Ramos', 'isabela@restaurorapido.com', 'password16', 19, 1);"
    };

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        for (String userInsert : INITIAL_USERS) {
            database.execSQL(userInsert);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(database);
    }

    public void addUser(String name, String email, String password, int points, String permissao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_POINTS, points);
        values.put(COLUMN_PERMISSAO, permissao);
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public static String getKeyNome() {
        return COLUMN_NAME;
    }

    public static String getKeySenha() {
        return COLUMN_PASSWORD;
    }

    public static String getKeyEmail() {
        return COLUMN_EMAIL;
    }

    public static String getTableUsers() {
        return TABLE_USERS;
    }

    public static String getKeyId() {
        return COLUMN_ID;
    }

    public static String getKeyPoints() {
        return COLUMN_POINTS;
    }

    public static String getKeyPermissao() {
        return COLUMN_PERMISSAO;
    }
}
