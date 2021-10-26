package com.example.lab1_gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Activity extends AppCompatActivity {
    static ArrayList<ModelOceny> mDane;
    Button avgbutton;
    RecyclerView mListaOcen;
    String[] nazwyPrzedmiotow;
    public String name,surname;
    public int subjects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        avgbutton = findViewById(R.id.avgbutton);
         Bundle pakunek = getIntent().getExtras();
         subjects = pakunek.getInt("Subjects");
         name = pakunek.getString("name");
         surname = pakunek.getString("surname");

    mDane = new ArrayList<>();
    nazwyPrzedmiotow = getResources().getStringArray(R.array.tablica);
    for(int i=1;i<=subjects;i++){
        mDane.add(new ModelOceny(nazwyPrzedmiotow[i]));}
        InteraktywnyAdapterTablicy adapter = new InteraktywnyAdapterTablicy(this,mDane);
        mListaOcen = findViewById(R.id.lista_ocen_rv);
        mListaOcen.setAdapter(adapter);
        mListaOcen.setLayoutManager(new LinearLayoutManager(this));


  avgbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            float srednia=0;
            for(int i=0;i<mDane.size();i++){
                srednia+=mDane.get(i).getOcena();
            }
            srednia = srednia/mDane.size();

            Intent intencja = new Intent(getApplicationContext(), MainActivity.class);
            intencja.putExtra("srednia",srednia);
            intencja.putExtra("Subjects",subjects);
            intencja.putExtra("name",name);
            intencja.putExtra("surname",surname);
            startActivity(intencja);
            finish();

        }
    });
    }
}
