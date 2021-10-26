package com.example.lab1_gui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InteraktywnyAdapterTablicy extends
        RecyclerView.Adapter<InteraktywnyAdapterTablicy.OcenyViewHolder>{
    private final List<ModelOceny> mListaocen;
    private final LayoutInflater mPompka;


    public InteraktywnyAdapterTablicy(Activity kontekst, List<ModelOceny> listaOcen) {
        mPompka = kontekst.getLayoutInflater();
        this.mListaocen = listaOcen;
    }

    @NonNull
    @Override
    public OcenyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        @SuppressLint("InflateParams") View wiersz = mPompka.inflate(R.layout.activity_view, null);
        return new OcenyViewHolder(wiersz);
    }



    @Override
    public void onBindViewHolder(@NonNull OcenyViewHolder ocenyViewHolder, int numerWiersza){
        ocenyViewHolder.mGrupaOceny.check(R.id.radioButton2);
        ocenyViewHolder.mGrupaOceny.setTag(mListaocen.get(numerWiersza));
        ocenyViewHolder.UstawWiersz(numerWiersza);
        ocenyViewHolder.nameView.setText(mListaocen.get(numerWiersza).getNazwa());

    }

    @Override
    public int getItemCount(){
        return mListaocen.size();
    }

    public class OcenyViewHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener {
        TextView nameView;
        RadioGroup mGrupaOceny;
        int numerWiersza;

        public OcenyViewHolder(@NonNull View glownyElementWiersza){
            super(glownyElementWiersza);
            nameView = glownyElementWiersza.findViewById(R.id.przedmiot);
            mGrupaOceny = glownyElementWiersza.findViewById(R.id.radioGroup);
            mGrupaOceny.setOnCheckedChangeListener(((group, checkedId) ->
            {
                View radioButton = mGrupaOceny.findViewById(checkedId);
                int index = mGrupaOceny.indexOfChild(radioButton);
                Activity.mDane.get(numerWiersza).setOcena(index+2);
            }));
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

        }
        public void UstawWiersz(int numerWiersza){
            this.numerWiersza=numerWiersza;
        }
    }


}
