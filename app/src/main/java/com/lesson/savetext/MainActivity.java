package com.lesson.savetext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText text;
    private Button saveButton,getButton;
    private TextView textView;
    final static String nameVariableKey="NAME_VARIABLE_KEY";
//    private String name;
    private SharedPreferences settings;
    final static String PREFS_FILE="Account";
    final static String PREF_NAME="Name";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        SharedPreferences.Editor prefEditor=settings.edit();
        prefEditor.putString(PREF_NAME,text.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String name=settings.getString(PREF_NAME,"Не определено");
        textView.setText(name);
        text.setText(name);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=findViewById(R.id.nameBox);
        saveButton=findViewById(R.id.saveButton);
        getButton=findViewById(R.id.getButton);
        textView=findViewById(R.id.textView);
        settings=getSharedPreferences(PREFS_FILE,MODE_PRIVATE);
        String name=settings.getString(PREF_NAME,"Не определено");
        textView.setText(name);
        text.setText(name);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=text.getText().toString();
                SharedPreferences.Editor prefEditor=settings.edit();
                prefEditor.putString(PREF_NAME,name);
                prefEditor.apply();
            }
        });
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=settings.getString(PREF_NAME,"Не определено");
                textView.setText(name);
            }
        });

    }
}