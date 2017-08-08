package com.example.ricardofernandes.tohomecliente;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ricardofernandes.tohomecliente.helper.SQLiteHandler;
import com.example.ricardofernandes.tohomecliente.helper.SessionManager;

import java.util.HashMap;

/**
 * Created by RicardoFernandes on 07/08/2017.
 */

public class DadosResidencia extends Activity {
    private TextView txtName;
    private TextView txtUsername;
    private TextView txtEmail;

    private SQLiteHandler db;
    private SessionManager session;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_residencia);
        setTheme(R.style.AppTheme_NoActionBar);

        txtName = (TextView) findViewById(R.id.name);
        txtUsername = (TextView) findViewById(R.id.username);
        txtEmail = (TextView) findViewById(R.id.email);

//        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());
//
//        // session manager
        session = new SessionManager(getApplicationContext());
//
        //
//        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();
        //
        String name = user.get("name");
        String username = user.get("username");
        String email = user.get("email");
//
//        // Displaying the user details on the screen
        txtName.setText(name);
        txtUsername.setText(username);
        txtEmail.setText(email);


        // Logout button click event

    }
}

