package com.alessiofurlan.mypersonalchef;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.alessiofurlan.mypersonalchef.model.Meals;
import com.squareup.picasso.Picasso;

import java.util.List;

import java.util.List;

public class IngredientiAdapter extends PagerAdapter {

        private List<Ingredienti.Ingrediente> ingredienti;
        private Context context;
        private static ClickListener clickListener;

        public IngredientiAdapter(List<Ingredienti.Ingrediente> ingredienti, Context context) {
            this.ingredienti = ingredienti;
            this.context = context;
        }

        public void setOnItemClickListener(ClickListener clickListener) {
            this.clickListener = clickListener;
        }

        @Override
        public int getCount() {
            return ingredienti.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view.equals(o);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = LayoutInflater.from(context).inflate(
                    R.layout.item_ingrediente,
                    container,
                    false
            );

            TextView nomeIngrediente = view.findViewById(R.id.nomeIngrediente);
            String strNomeIngrediente = ingredienti.get(position).getIngrediente();
            nomeIngrediente.setText(strNomeIngrediente);

            TextView quantitaIngrediente = view.findViewById(R.id.quantitaIngrediente);
            String strQuantitaIngrediente = ingredienti.get(position).getQuantita();
            nomeIngrediente.setText(strNomeIngrediente);
            container.addView(view, 0);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }

        public interface ClickListener {
            void onClick(View v, int position);
            void inizioCaricamento(View v);
            void fineCericamento(View v);
        }


}
