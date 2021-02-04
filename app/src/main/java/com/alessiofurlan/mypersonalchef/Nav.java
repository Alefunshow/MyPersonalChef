package com.alessiofurlan.mypersonalchef;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class Nav extends AppCompatActivity {
    ChipNavigationBar bottomNav;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity);
        bottomNav = findViewById(R.id.bottom_nav);
        if(savedInstanceState==null){
            bottomNav.setItemSelected(R.id.ricette,true);
            fm = getSupportFragmentManager();
            RicetteFragment rF = new RicetteFragment();
            fm.beginTransaction()
                    .replace(R.id.fragment_container,rF)
                    .commit();
        }
        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment f = null;
                switch(id){
                    case R.id.ricette:
                        f = new RicetteFragment();
                        break;
                    case R.id.cerca:
                        f = new CercaFragment();
                        break;
                    case R.id.profilo:
                        f = new ProfiloFragment();
                        break;
                }
                if (f != null) {
                    fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.fragment_container,f)
                            .commit();
                }else{
                    Log.e("ERRORE MENU","errore nel creare frammento");
                }
            }
        });
    }
}