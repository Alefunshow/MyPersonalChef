package com.alessiofurlan.mypersonalchef;

import android.app.SearchManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.alessiofurlan.mypersonalchef.model.Meals;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CercaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CercaFragment extends Fragment {

    private RecyclerView recyclerView;
    private CercaAdapter cercaAdapter;
    private List<Meals.Meal> meals;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CercaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CercaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CercaFragment newInstance(String param1, String param2) {
        CercaFragment fragment = new CercaFragment();
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
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_cerca, container, false);
        recyclerView = view.findViewById(R.id.recyclerRicerca);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        SearchView searchView = view.findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                try{
                    Ricerca(query);
                }catch (Exception e){
                    Toast.makeText(getContext(),"aa",Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try{
                    Ricerca(newText);
                }catch (Exception e){
                Toast.makeText(getContext(),"aa",Toast.LENGTH_SHORT).show();
            }
                return false;
            }
        });
       return view;
    }

    public void Ricerca(String nomePiatto){

            Call<Meals> mealsCall = Utils.getApi().getMealByName(nomePiatto);

            mealsCall.enqueue(new Callback<Meals>() {
                @Override
                public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Meals.Meal> meals = response.body().getMeals();
                        recyclerView.setAdapter(new CercaAdapter(getContext(),meals));
                    }
                    else {
                        Log.w("ERRORE", "errore nella risposta");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) {
                    Toast.makeText(getContext(),"aa",Toast.LENGTH_SHORT).show();
                }
            });


    }
}