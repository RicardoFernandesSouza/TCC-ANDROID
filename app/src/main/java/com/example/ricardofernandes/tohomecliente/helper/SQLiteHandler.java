package com.example.ricardofernandes.tohomecliente.helper;

/**
 * Created by RicardoFernandes on 03/06/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "tohomecrud";

    // Login table name
    private static final String TABLE_CLIENTE = "cliente";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USER_NAME = "username";
    private static final String KEY_USER_PASSWORD = "password";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_CLIENTE + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"  + KEY_NAME + " TEXT,"
                + KEY_USER_NAME + " TEXT," + KEY_USER_PASSWORD + " TEXT," +  KEY_EMAIL + " TEXT" +  ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        //AQUI PASSAMOS TODAS AS OUTRAS TABELAS QUE TEM NO BANCO

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTE);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database PODEMOS USAR ESSE METODO PARA FAZER O UPDATE DAS ETAPAS
     * */
    public void addUser(String id, String name, String username,String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Name
        values.put(KEY_USER_NAME, username); // NOME DE USUÁRIO
        values.put(KEY_USER_PASSWORD, password); // SENHA
        values.put(KEY_EMAIL, email); // Email




        // Inserting Row
        long uid = db.insert(TABLE_CLIENTE, null, values);
        values.put(KEY_ID, id); // Email

        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + uid);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_CLIENTE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("id", cursor.getString(0));
            user.put("name", cursor.getString(1));
            user.put("username", cursor.getString(2));
            user.put("password", cursor.getString(3));
            user.put("email", cursor.getString(4));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_CLIENTE, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }

}