package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
// blah blah blah

    private Button[][] buttons = new Button[3][3];


    private boolean Player1Turn = true;

    private int RoundCount;

    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                String buttonID = "Button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset = findViewById(R.id.Button_Reset);
        buttonReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                player1Points = 0;
                player2Points = 0;
                updatePointsText();
                resetBoard();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v) .getText().toString().equals("")){
            return;
        }

        if (Player1Turn){
            ((Button)v).setText("X");
            v.setBackgroundResource(R.drawable.york);
        } else {
            ((Button)v).setText("O");
            v.setBackgroundResource(R.drawable.lancaster);
        }
        RoundCount++;

        if (checkforWin()) {

            if (Player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        }
        else if(RoundCount == 9){
                draw();
            }
            else{
                Player1Turn = !Player1Turn;
            }

    }

    private boolean checkforWin(){
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++){
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")){
                return true;
            }
        }

        for (int i = 0; i < 3; i++){
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")){
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")){
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")){
            return true;
        }

        return false;
    }

    private void player1Wins(){
        player1Points++;
        Toast.makeText(this, "York is victorious!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void player2Wins(){
        player2Points++;
        Toast.makeText(this, "Lancaster is victorious!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void draw(){
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText(){
        textViewPlayer1.setText("York: " + player1Points);
        textViewPlayer2.setText("Lancaster: " + player2Points);
    }
    private void resetBoard(){
        RoundCount = 0;
        Player1Turn = true;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                buttons[i][j].setText("");
                buttons[i][j].setBackgroundResource(0);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putInt("RoundCount", RoundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("Player1Turn", Player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        RoundCount = savedInstanceState.getInt("RoundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        Player1Turn = savedInstanceState.getBoolean("Player1Turn");
    }
}
