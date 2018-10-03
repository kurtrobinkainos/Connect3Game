package com.example.kurtisr.tictacapp;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0 for yellow, 1 for red.
    int activePlayer = 0;
    //2 To represent an empty counter space.
    int [] gameState = {2,2,2,2,2,2,2,2,2};

    public void playAgain(View view){
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;

        //Resets gamestate to none
        for (int i = 0; i < gameState.length; i++)
        {
            gameState[i] = 2;
        }

        android.support.v7.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        //Resets images to empty
        for(int i = 0; i < gridLayout.getChildCount(); i++)
        {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }



    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int counterPosition = Integer.parseInt(counter.getTag().toString());
        int [][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

        if(gameState[counterPosition] == 2) {

            gameState[counterPosition] = activePlayer;

            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);

            for (int[] winningPosition : winningPositions)
            {
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2)


                if(gameState[winningPosition[0]] == 0)
                {
                    Toast.makeText(MainActivity.this, "Yellow Has Won!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Red Has Won!", Toast.LENGTH_SHORT).show();
                }

                {
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                    if(layout.getVisibility()==View.INVISIBLE)
                    {
                        layout.setVisibility(View.VISIBLE);
                    }
                }

            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
