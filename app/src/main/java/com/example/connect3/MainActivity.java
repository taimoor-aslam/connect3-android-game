package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
ImageView imageView;

int activePlayer=0; // 0 for yellow, 1 for red

int gameState[]={2,2,2,2,2,2,2,2,2}; //to ensure user can tap only once, could be any number except 0,1

int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};

boolean gameIsActive=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void dropIn(View view) {

        imageView = (ImageView) view;

        int tappedCount = Integer.parseInt(imageView.getTag().toString());

        if (gameState[tappedCount] == 2&&gameIsActive) {

            gameState[tappedCount]=activePlayer;

            imageView.setTranslationY(-1000f);

            if (activePlayer == 0) {

                imageView.setImageResource(R.drawable.yellow);

                activePlayer = 1;

              //  gameState[tappedCount]=0;
            } else {

                imageView.setImageResource(R.drawable.red);

                activePlayer = 0;
               // gameState[tappedCount]=1;
            }

            imageView.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int[] winningPosition:winningPositions)
            {
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]]
                        &&gameState[winningPosition[1]]== gameState[winningPosition[2]]
                        &&gameState[winningPosition[0]]!=2)
                {
                    gameIsActive=false;

                    String winnerMessage="Red";

                    if(gameState[winningPosition[0]]==0)
                    {

                        winnerMessage="Yellow";
                    }
                    TextView textView=(TextView) findViewById(R.id.textview);

                    textView.setText(winnerMessage+" has won!");

                    LinearLayout linearLayout=(LinearLayout) findViewById(R.id.linearlayout);

                    linearLayout.setVisibility(View.VISIBLE);
//                    Toast.makeText(getApplicationContext(),"Win!",Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean gameIsOver=true;

                    for(int countState:gameState)
                    {
                        if(countState==2)
                        {
                            gameIsOver=false;
                        }
                    }
                    if(gameIsOver)
                    {
                        TextView textView=(TextView) findViewById(R.id.textview);

                        textView.setText("It's a draw!");

                        LinearLayout linearLayout=(LinearLayout) findViewById(R.id.linearlayout);

                        linearLayout.setVisibility(View.VISIBLE);
                    }
                }
            }

        }

    }

    public void playAgain(View view)
    {
        gameIsActive=true;

        Button playAgain=(Button) findViewById(R.id.button);

        LinearLayout linearLayout=(LinearLayout) findViewById(R.id.linearlayout);

        linearLayout.setVisibility(View.INVISIBLE);

       for(int i=0;i<gameState.length;i++)
       {
           gameState[i]=2;
       }
        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridlayout);

       for(int i=0;i<gridLayout.getChildCount();i++)
       {
           ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
       }
    }
}