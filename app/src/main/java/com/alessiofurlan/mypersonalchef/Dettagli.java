package com.alessiofurlan.mypersonalchef;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alessiofurlan.mypersonalchef.model.Meals;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dettagli extends AppCompatActivity {
    Toolbar toolbar;
    ImageView immaginePiatto;
    TextView nomePiatto, txtProcedimento;
    List<Ingredienti.Ingrediente> ingredienti;
    private ViewPager ingredientiView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli);
        ingredientiView = findViewById(R.id.ingredienteViewPager);
        ingredientiView.setPadding(20, 0, 150, 0);
        immaginePiatto = findViewById(R.id.immaginePiatto1);
        nomePiatto = findViewById(R.id.nomePiatto);
        txtProcedimento = findViewById(R.id.txtProcedimento);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back); // Set the icon
        // Icon click listener
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ret = new Intent(getApplicationContext(),Nav.class);
                startActivity(ret);
            }
        });
        Intent intent = getIntent();
        getMealById(intent.getStringExtra("nomePiatto"));

    }

    void getMealById(String mealName) {

        Utils.getApi().getMealByName(mealName).enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Meals.Meal meal = response.body().getMeals().get(0);

                    toolbar.setTitle("Dettagli");
                    String urlImmagine = meal.getStrMealThumb();
                    nomePiatto.setText(meal.getStrMeal());
                    txtProcedimento.setText(meal.getStrInstructions());
                    Picasso.get().load(urlImmagine).fit().centerInside().into(immaginePiatto);

                } else {
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {

            }
        });

    }

}