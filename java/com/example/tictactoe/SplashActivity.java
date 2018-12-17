package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tictactoe.MainActivity;
import com.example.tictactoe.R;

public class SplashActivity extends AppCompatActivity {
// blah blah blah

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
        finish();
    }
}