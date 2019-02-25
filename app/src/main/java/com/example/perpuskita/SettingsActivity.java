package com.example.perpuskita;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    SharedPreferences myPrefs;
    Switch switch_email;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);
        editor = myPrefs.edit();
        switch_email= (Switch) findViewById(R.id.switch_email);

        if (switch_email != null) {
            switch_email.setOnCheckedChangeListener(this);
        }

//         ambil data boolean dr shared preferences
        boolean check = myPrefs.getBoolean("prefKey", false);
        switch_email.setChecked(check);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Toast.makeText(this, "The Switch is " + (isChecked ? "on" : "off"),
                Toast.LENGTH_SHORT).show();
        if(isChecked) {
            //do stuff when Switch is ON
            editor.putBoolean("prefKey",true);
        } else {
            //do stuff when Switch if OFF
            editor.putBoolean("prefKey",false);
        }
        editor.commit();
    }
}




