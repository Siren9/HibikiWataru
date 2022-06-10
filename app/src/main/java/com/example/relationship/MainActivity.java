package com.example.relationship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.relationship.activity.ClockActivity;
import com.example.relationship.activity.DictionaryActivity;
import com.example.relationship.activity.MusicActivity;
import com.example.relationship.activity.NoteActivity;
import com.example.relationship.activity.PictureActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login = findViewById(R.id.main_but_next);
        setListen();
    }

    private void setListen(){
        onClick onClick = new onClick();
        Login.setOnClickListener(onClick);
    }

    private class onClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()){
                case R.id.main_but_next:
                    Toast.makeText(MainActivity.this,"Login in. Anything there.",
                            Toast.LENGTH_SHORT).show();
                    intent = new Intent("MENU");
                    startActivity(intent);
                    break;
            }
        }
    }

}