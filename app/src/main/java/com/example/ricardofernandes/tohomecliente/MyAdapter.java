package com.example.ricardofernandes.tohomecliente;

/**
 * Created by RicardoFernandes on 07/06/2017.
 */


import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by RicardoFernandes on 08/05/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Projeto> projetos;
    private Activity activity;
    public MyAdapter(Activity activity, List<Projeto> projetos) {
        this.projetos = projetos;
        this.activity = activity;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view =inflater.inflate(R.layout.item_recycler, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int position) {
        viewHolder.name.setText(projetos.get(position).getName());
        viewHolder.address.setText(projetos.get(position).getAddress());
        if (projetos.get(position).isGender()) {

            // viewHolder.imageView.setImageResource(R.drawable.male);
        }else {
            // viewHolder.imageView.setImageResource(R.drawable.female);
        }
        viewHolder.container.setOnClickListener(onClickListener(position));
    }
    private void setDataToView(TextView name, TextView address, ImageView genderIcon, int position){
        name.setText(projetos.get(position).getName());
        address.setText(projetos.get(position).getAddress());
        if (projetos.get(position).isGender()) {
            //  genderIcon.setImageResource(R.drawable.male);
        }else{
            //   genderIcon.setImageResource(R.drawable.female);
        }
    }
    @Override
    public int getItemCount() {
        return (null !=projetos?projetos.size():0);
    }
    private View.OnClickListener onClickListener(final int position){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(v.getContext(), MainActivity.class);
                // v.getContext().startActivity(intent1);
                v.getContext().startActivity( intent1 );
//                final Dialog dialog = new Dialog(activity);
//                dialog.setContentView(R.layout.item_recycler);
//                dialog.setTitle("Position" + position);
//                dialog.setCancelable(true);
//                TextView name =(TextView)dialog.findViewById(R.id.name);
//                TextView address=(TextView)dialog.findViewById(R.id.address);
//                ImageView icon = (ImageView) dialog.findViewById(R.id.image);
//                setDataToView(name,address,icon,position);
//                dialog.show();

            }
        };
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name;
        private  TextView address;
        private View container;
        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image);
            name = (TextView) view.findViewById(R.id.name);
            address = (TextView) view.findViewById(R.id.address);
            container = view.findViewById(R.id.card_view);
        }
    }
}