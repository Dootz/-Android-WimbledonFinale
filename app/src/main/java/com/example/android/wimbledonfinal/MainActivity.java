package com.example.android.wimbledonfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static android.R.attr.handle;

public class MainActivity extends AppCompatActivity {

    int player1Score = 0;
    int player2Score = 0;
    int points30 = 30;
    int points40 = 40;
    int pointsAdvantage = 45;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void player1Score(View view)
    {
        if(player1Score == points30 && player2Score < points40) {
            player1Score = points40;
            updateScore();
        }
        else if(player1Score == points40 && player2Score < points40)
            announceWinner("Winner: Player 1");
        else if(player1Score == points30 && player2Score == points40){
            deuce();
            player1Score = points40;
        }
        else if(player1Score == points40 && player2Score == points40){
            advantage(1);
            player1Score += 5;
        }
        else if(player1Score == pointsAdvantage && player2Score == points40){
            announceWinner("Winner: Player 1");
        }
        else if(player1Score == points40 && player2Score == pointsAdvantage)
        {
            deuce();
            player2Score -= 5;
        }
        else {
            player1Score += 15;
            updateScore();
        }
    }

    public void player2Score(View view)
    {
        if(player2Score == points30 && player1Score < points40) {
            player2Score = points40;
            updateScore();
        }
        else if(player2Score == points40 && player1Score < points40)
            announceWinner("Winner: Player 2");
        else if(player2Score == points30 && player1Score == points40){
            deuce();
            player2Score = points40;
        }
        else if(player2Score == points40 && player1Score == points40){
            advantage(2);
            player2Score += 5;
        }
        else if(player2Score == pointsAdvantage && player1Score == points40){
            announceWinner("Winner: Player 2");
        }
        else if(player2Score == points40 && player1Score == pointsAdvantage)
        {
            deuce();
            player1Score -= 5;
        }
        else {
            player2Score += 15;
            updateScore();
        }
    }

    private void updateScore()
    {
        TextView player1TextView = (TextView) findViewById(R.id.player1PointsTextView);
        TextView player2TextView = (TextView) findViewById(R.id.player2PointsTextView);
        player1TextView.setText(""+ player1Score);
        player2TextView.setText(""+ player2Score);
    }

    private void announceWinner(String playerName)
    {
        TextView winnerTextView = (TextView) findViewById(R.id.textView);
        winnerTextView.setText(playerName);
    }

    private void deuce()
    {
        TextView player1TextView = (TextView) findViewById(R.id.player1PointsTextView);
        TextView player2TextView = (TextView) findViewById(R.id.player2PointsTextView);
        player1TextView.setText("D");
        player2TextView.setText("D");
    }

    private void advantage(int player)
    {
        TextView player1TextView = (TextView) findViewById(R.id.player1PointsTextView);
        TextView player2TextView = (TextView) findViewById(R.id.player2PointsTextView);
        if(player == 1) {
            player1TextView.setText("A");
            player2TextView.setText("-");
        }
        else if(player == 2) {
            player1TextView.setText("-");
            player2TextView.setText("A");
        }
    }

    public void resetScore(View view)
    {
        player1Score = 0;
        player2Score = 0;
        updateScore();
}
}
