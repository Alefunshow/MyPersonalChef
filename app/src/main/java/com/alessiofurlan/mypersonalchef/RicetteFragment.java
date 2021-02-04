package com.alessiofurlan.mypersonalchef;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alessiofurlan.mypersonalchef.model.Categories;
import com.alessiofurlan.mypersonalchef.model.Meals;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RicetteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RicetteFragment extends Fragment {

    private RecyclerView recyclerView;
    private ViewPager evidenzaView;
    private RicetteAdapter ricetteAdapter;
    private ArrayList<CardRicetta> listaRicette;
    private RequestQueue requestQueue;
    EvidenzaAdapter adapter;
    ImageView immagineEvidenza;
    TextView nomeEvidenza;
    private static final String TAG = "DB";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RicetteFragment() {
        // Required empty public constructor
    }


    public static RicetteFragment newInstance(String param1, String param2) {
        RicetteFragment fragment = new RicetteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ricette, container, false);
        showLoading(view);
        immagineEvidenza = view.findViewById(R.id.mealThumb);
        nomeEvidenza = view.findViewById(R.id.mealName);
        recyclerView = view.findViewById(R.id.recyclerview);
        evidenzaView = view.findViewById(R.id.evidenzaViewPager);
        evidenzaView.setPadding(20, 0, 150, 0);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listaRicette = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(view.getContext());
        getEvidenza();
        parseJSON();
        return view;
    }

    private void parseJSON() {

        Call<Meals> mealsCall = Utils.getApi().getRandomMeal();
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                if (response.isSuccessful() && response.body() != null) {
                    hideLoading(getView());
                    List<Meals.Meal> meals = response.body().getMeals();
                    recyclerView.setAdapter(new RicetteAdapter(getContext(),meals));
                }
                else {
                    Log.w("ERRORE", "errore nella risposta");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) {
                hideLoading(getView());
            }
        });
    }

    public void showLoading(View v){
        v.findViewById(R.id.shimmerEvidenza).setVisibility(View.VISIBLE);
        v.findViewById(R.id.shimmerRicette).setVisibility(View.VISIBLE);
    }
    public void hideLoading(View v){

        v.findViewById(R.id.shimmerEvidenza).setVisibility(View.GONE);
        v.findViewById(R.id.shimmerRicette).setVisibility(View.GONE);
    }
    private void getEvidenza() {

        Call<Meals> mealsCall = Utils.getApi().getRandomMeal();
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                if (response.isSuccessful() && response.body() != null) {

                    List<Meals.Meal> meals = response.body().getMeals();
                    evidenzaView.setAdapter(new EvidenzaAdapter(meals,getContext()));

                }
                else {
                    Log.w("ERRORE", "errore nella risposta");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) {
            }
        });
    }


}