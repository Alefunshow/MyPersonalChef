package com.alessiofurlan.mypersonalchef;

import com.alessiofurlan.mypersonalchef.model.Meals;

import java.util.List;

public class Ingredienti {
    private List<Ingrediente> ingredienti = null;
    public List<Ingrediente> getIngredienti(){
        return this.ingredienti;
    }


    public static class Ingrediente{
        private String ingrediente;
        private String quantita;
        public Ingrediente(String ingrediente,String quantita){
            this.ingrediente = ingrediente;
            this.quantita = quantita;
        }

        public String getIngrediente() {
            return ingrediente;
        }

        public String getQuantita() {
            return quantita;
        }
    }

}
