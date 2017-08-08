package com.example.ricardofernandes.tohomecliente;

/**
 * Created by RicardoFernandes on 21/05/2017.
 */

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static java.util.Calendar.getInstance;

public class MarcaVisita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marca_visita);
        final Calendar cal = getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();

    }

    Button mbtn;
    int year;

    public int getMonth() {
        return month+1;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public TimePickerDialog.OnTimeSetListener getTpickerListener() {
        return tpickerListener;
    }

    public DatePickerDialog.OnDateSetListener getDpickerListener() {
        return dpickerListener;
    }

    int month;
    int day;
    int hour,minute;

    public void showDialogOnButtonClick() {

        mbtn = (Button) findViewById(R.id.ibtn1);

        mbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog(0);
                    }
                }
        );

    }

    public void showAlert() {
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setMessage("Informe a Hora")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showDialog(1);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialogInterface, int i1){
                        dialogInterface.dismiss();
                    }
                })
                .setTitle("Hora")
                .create();
        myAlert.show();

    }

    protected Dialog onCreateDialog(int id){
        if(id == 0){
            return new DatePickerDialog(this, dpickerListener,year, month, day);
        }
        if(id == 1) {
            return new TimePickerDialog(this, tpickerListener, hour, minute, false);
        }

        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            year = i;
            month = i1;
            day = i2;
            // Toast.makeText(MarcaVisita.this,year+"/"+month+"/"+day, Toast.LENGTH_SHORT).show();
            showAlert();

        }
    };
    private TimePickerDialog.OnTimeSetListener tpickerListener
            = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker timePicker, int h, int m) {
            hour = h;
            minute = m;
            if (minute < 10) {
                // Toast.makeText(MarcaVisita.this, hour + ":" + "0"+minute, Toast.LENGTH_SHORT).show();
                sendEmail();

                // showAlert();
            }
            else
                // Toast.makeText(MarcaVisita.this, hour + ":" + minute, Toast.LENGTH_SHORT).show();
                sendEmail();

            // showAlert();

        }
    };


    protected void sendEmail() {
        Log.i("Send email", "");
        //  int dia,mes,ano,hora,minuto;
        String[] TO = {"ricardo_fernandes.souza@hotmail.com"};
        String[] CC = {"recardenho@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Visita na Obra");
        if(getMinute()<10){
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Olá, vou visitar a obra na data: "+ getDay() + "/"+ getMonth() + "/" + getYear() + "\nNo horário das: " + getHour()+":"+"0"+getMinute()+"\nEsse é um e-mail automático do App ToHome");
        }
        else {
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Olá, vou visitar a obra na data: " + getDay() + "/" + getMonth() + "/" + getYear() + "\nNo horário das: " + getHour() + ":" + getMinute() + "\nEsse é um e-mail automático do App ToHome");
        }
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished send email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MarcaVisita.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}

