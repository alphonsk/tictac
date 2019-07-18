package com.westphillylabs.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import	androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winningPostions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    boolean gameActive = true;

    public void drop(View view){

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        Button restartBttn = findViewById(R.id.restartBttn);

        if ((gameState[tappedCounter] == 2) && (gameActive == true)) {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);
                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 0;

            }


            counter.animate().translationY(10).setDuration(400);

            for (int[] x : winningPostions) {
                if (gameState[x[0]] == gameState[x[1]] && gameState[x[1]] == gameState[x[2]] && gameState[x[0]] != 2) {

                    if (activePlayer == 1) {
                        gameActive = false;
                        winnerTextView.setText("Red has won");
                        restartBttn.setVisibility(View.VISIBLE);

//                        Toast.makeText(this, "red won", Toast.LENGTH_LONG).show();
                    } else {
                        gameActive = false;
                        winnerTextView.setText("Yellow has won");
                        restartBttn.setVisibility(View.VISIBLE);

//                        Toast.makeText(this, "yellow worn", Toast.LENGTH_LONG).show();

                    }

                }
            }
        }

    }



    public void reStart(View view){
        Button restartBttn = findViewById(R.id.restartBttn);
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        restartBttn.setVisibility(View.INVISIBLE);
        winnerTextView.setText("");

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i =0; i< gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        // int [] gameState = {2,2,2,2,2,2,2,2,2};
        for (int i =0 ; i < gameState.length;i++){
            gameState[i] = 2;
        }
        gameActive = true;
        activePlayer = 0;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
