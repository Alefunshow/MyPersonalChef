package com.alessiofurlan.mypersonalchef;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alessiofurlan.mypersonalchef.model.Meals;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CercaAdapter extends RecyclerView.Adapter<CercaAdapter.CercaViewHolder> {
    private Context context;
    private List<Meals.Meal> meals;
    public CercaAdapter(Context context,List<Meals.Meal> meals){
        this.context = context;
        this.meals = meals;

    }

    @NonNull
    @Override
    public CercaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_ricetta,parent,false);

        return new CercaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CercaViewHolder holder, int position) {
        String urlImmagine = meals.get(position).getStrMealThumb();
        String nomePiatto = meals.get(position).getStrMeal();
        String categoriaPiatto = meals.get(position).getStrCategory();
        holder.nomePiatto.setText(nomePiatto);
        holder.categoriaPiatto.setText(categoriaPiatto);
        Picasso.get().load(urlImmagine).fit().centerInside().into(holder.immaginePiatto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),Dettagli.class);
                i.putExtra("nomePiatto",nomePiatto);
                context.startActivity(i);
                //Toast.makeText(context,nomePiatto+"   "+String.valueOf(holder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class CercaViewHolder extends RecyclerView.ViewHolder{
        public ImageView immaginePiatto;
        public TextView nomePiatto;
        public TextView categoriaPiatto;
        public CercaViewHolder(@NonNull View itemView) {
            super(itemView);
            immaginePiatto = itemView.findViewById(R.id.immaginePiatto);
            nomePiatto = itemView.findViewById(R.id.nomeRicetta);
            categoriaPiatto = itemView.findViewById(R.id.categoriaPiatto);
        }


    }
}