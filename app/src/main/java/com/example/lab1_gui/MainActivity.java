package com.example.lab1_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    EditText NameInput, LastnameInput, NumberInput;
    TextView Srednia;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NameInput = findViewById(R.id.NameInput);
        LastnameInput = findViewById(R.id.LastnameInput);
        NumberInput = findViewById(R.id.NumberInput);
        submit = findViewById(R.id.submit);
        Srednia = findViewById(R.id.textView4);



        NameInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateInput(NameInput);
                    if(validateInput(NameInput) && validateInput(LastnameInput) && validateNumber(NumberInput)){
                        submit.setVisibility(View.VISIBLE);}
                    else{
                        submit.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        LastnameInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateInput(LastnameInput);
                    if(validateInput(NameInput) && validateInput(LastnameInput) && validateNumber(NumberInput)){
                        submit.setVisibility(View.VISIBLE);}
                    else{
                        submit.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        NumberInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateNumber(NumberInput);
                    if(validateInput(NameInput) && validateInput(LastnameInput) && validateNumber(NumberInput)){
                        submit.setVisibility(View.VISIBLE);}
                    else{
                        submit.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                int Subjects = Integer.parseInt(NumberInput.getText().toString());
                String name = NameInput.getText().toString();
                String surname = LastnameInput.getText().toString();

                Intent intent = new Intent(MainActivity.this, Activity.class);
                intent.putExtra("Subjects", Subjects);
                intent.putExtra("name", name);
                intent.putExtra("surname", surname);
                startActivityForResult(intent, RESULT_OK);

            }
        });

    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume()
    {

        super.onResume();
        Bundle pakunek = getIntent().getExtras();
        if(pakunek!=null)
        {
        TextView sr = findViewById(R.id.textView4);
        sr.setVisibility(View.VISIBLE);
            float srednia = pakunek.getFloat("srednia");
            int subjects = pakunek.getInt("Subjects");
            String resName = pakunek.getString("name");
            String resSurname = pakunek.getString("surname");
            NameInput.setText(resName);
            LastnameInput.setText(resSurname);
            NumberInput.setText("" + subjects);
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            sr.setText("Twoja srednia to "+ df.format(srednia));

            submit.setVisibility(View.VISIBLE);
            if(srednia>=3){
                submit.setText("SUPER!");
                }
            else{
                submit.setText("Tym razem mi nie poszło");}

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(srednia>=3){
                        showToast("Gratulacje! Otrzymujesz zaliczenie!");
                    }
                    else{
                        showToast("Wysylam podanie o zaliczenie warunkowe");
                    }
                    finishAffinity();
                }

            });
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){

        TextView NameInput = findViewById(R.id.NameInput);
        TextView LastnameInput = findViewById(R.id.LastnameInput);
        TextView NumberInput = findViewById(R.id.NumberInput);

        outState.putString("name",NameInput.getText().toString());
        outState.putString("lastname",LastnameInput.getText().toString());
        outState.putString("note",NumberInput.getText().toString());

        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        TextView NameInput = findViewById(R.id.NameInput);
        TextView LastnameInput = findViewById(R.id.LastnameInput);
        TextView NumberInput = findViewById(R.id.NumberInput);

        NameInput.setText(savedInstanceState.getString("name"));
        LastnameInput.setText(savedInstanceState.getString("lastname"));
        NumberInput.setText(savedInstanceState.getString("note"));

    }
    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

   
    private boolean validateInput(EditText input) {
        String nameInput = input.getText().toString();
        if(!nameInput.isEmpty()) {

            return true;
        }
        else{
            input.setError("Pole nie moze byc puste");
            showToast("Pole nie moze byc puste");
                return false;
        }
    }
    private boolean validateNumber(EditText input) {
        String nameInput = input.getText().toString();

        if(nameInput.isEmpty()) {
            input.setError("Pole nie moze byc puste");
            showToast("Pole nie moze byc puste");
            return false;
        }
        int finalValue = Integer.parseInt(nameInput);
        if(finalValue<5 || finalValue>15) {
            input.setError("Zakres 5-15");
            showToast("Wprowadzona bledne dane");
            return false;
        }
        else{

            return true;
        }
    }



}