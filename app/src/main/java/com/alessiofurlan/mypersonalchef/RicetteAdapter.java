package com.alessiofurlan.mypersonalchef;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alessiofurlan.mypersonalchef.model.Meals;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class RicetteAdapter extends RecyclerView.Adapter<RicetteAdapter.RicetteViewHolder> {
    private Context context;
    private ArrayList<CardRicetta> listaRicette;
    private List<Meals.Meal> meals;
    public RicetteAdapter(Context context,List<Meals.Meal> meals){
        this.context = context;
        this.meals = meals;

    }

    @NonNull
    @Override
    public RicetteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_ricetta,parent,false);

        return new RicetteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RicetteViewHolder holder, int position) {
        String urlImmagine = meals.get(position).getStrMealThumb();
        String nomePiatto = meals.get(position).getStrMeal();
        String categoriaPiatto = meals.get(position).getStrCategory();
        holder.nomePiatto.setText(nomePiatto);
        holder.categoriaPiatto.setText(categoriaPiatto);
        Picasso.get().load(urlImmagine).fit().centerInside().into(holder.immaginePiatto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,nomePiatto+"   "+String.valueOf(holder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class RicetteViewHolder extends RecyclerView.ViewHolder{
        public ImageView immaginePiatto;
        public TextView nomePiatto;
        public TextView categoriaPiatto;
        public RicetteViewHolder(@NonNull View itemView) {
            super(itemView);
            immaginePiatto = itemView.findViewById(R.id.immaginePiatto);
            nomePiatto = itemView.findViewById(R.id.nomeRicetta);
            categoriaPiatto = itemView.findViewById(R.id.categoriaPiatto);
        }


    }
}
