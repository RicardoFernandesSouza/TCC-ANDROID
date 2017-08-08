package com.example.ricardofernandes.tohomecliente;

/**
 * Created by RicardoFernandes on 07/06/2017.
 */


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;

public class Tab2Status extends Fragment implements OnClickListener {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab2status, container, false);
        Button button = (Button)rootView.findViewById(R.id.btnEtapas);
        Button marcaVisitaBtn = (Button)rootView.findViewById(R.id.btnMarcaVisita);
        Button tiraFoto = (Button)rootView.findViewById(R.id.btnTiraFoto);
        button.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v){
                switch(v.getId()){

                    case R.id.btnEtapas:
                        Intent intent1 = new Intent(rootView.getContext(), Etapas.class);
                        rootView.getContext().startActivity(intent1);
                        break;
                }
            }
        });

        marcaVisitaBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v){
                switch(v.getId()){

                    case R.id.btnMarcaVisita:
                        Intent intent3 = new Intent(rootView.getContext(), MarcaVisita.class);
                        rootView.getContext().startActivity(intent3);
                        break;
                }
            }
        });

        tiraFoto.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v){
                switch(v.getId()){

                    case R.id.btnTiraFoto:
                        Intent intent4 = new Intent(rootView.getContext(), TiraFoto.class);
                        rootView.getContext().startActivity(intent4);
                        break;
                }
            }
        });

        return rootView;

    }

    @Override
    public void onClick(View v) {

    }



}

