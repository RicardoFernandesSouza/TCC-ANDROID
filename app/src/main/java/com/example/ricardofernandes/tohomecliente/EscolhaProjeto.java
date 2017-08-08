package com.example.ricardofernandes.tohomecliente;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ricardofernandes.tohomecliente.activity.LoginActivity;
import com.example.ricardofernandes.tohomecliente.helper.SQLiteHandler;
import com.example.ricardofernandes.tohomecliente.helper.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;


public class EscolhaProjeto extends Activity {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private ArrayList<Projeto> projetoArrayList;
   // private FloatingActionButton feb;
    private boolean gender;

    private TextView txtName;
    private TextView txtUsername;
    private Button btnLogout;

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_projeto);
        projetoArrayList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyle_view);
        //feb = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        setRecyclerViewData();
        adapter = new MyAdapter(this, projetoArrayList);
        recyclerView.setAdapter(adapter);
       // feb.setOnClickListener(onAddingListener());
        setTheme(R.style.AppTheme_NoActionBar);

        txtName = (TextView) findViewById(R.id.name);
        txtUsername = (TextView) findViewById(R.id.username);
        btnLogout = (Button) findViewById(R.id.btnLogout);
//
//        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());
//
//        // session manager
        session = new SessionManager(getApplicationContext());
//
       if (!session.isLoggedIn()) {
            logoutUser();
        }
//
//        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();
//
        String name = user.get("name");
        String username = user.get("username");
//
//        // Displaying the user details on the screen
        txtName.setText(name);
        txtUsername.setText(username);

        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

    private void setRecyclerViewData() {
        projetoArrayList.add(new Projeto("Rua Sete de Setembro, 567, Centro - Salto/SP", false, ""));
        projetoArrayList.add(new Projeto("Rua 23 de Maio, 199, Centro - Salto/SP", true, ""));
        projetoArrayList.add(new Projeto("Rua 17 de Abril, 1996, Centro - Indaiatuba/SP", true, ""));

    }


    private AdapterView.OnItemSelectedListener onItemSelectedListener() {

        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {

                    gender = true;
                } else {
                    gender = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
    }

    private View.OnClickListener onConfirmListener(final EditText name, final EditText address, final Dialog dialog) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Projeto friend = new Projeto(name.getText().toString().trim(), gender, address.getText().toString().trim());
                projetoArrayList.add(friend);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        };
    }

    private View.OnClickListener onCancelListener(final Dialog dialog) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        };
    }



    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(EscolhaProjeto.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
