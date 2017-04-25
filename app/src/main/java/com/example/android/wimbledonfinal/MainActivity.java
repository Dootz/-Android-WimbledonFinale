package com.example.android.wimbledonfinal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.handle;
import static com.example.android.wimbledonfinal.R.string.player1;
import static com.example.android.wimbledonfinal.R.string.player2;

public class MainActivity extends AppCompatActivity {
    private int player1Score = 0;
    private int player2Score = 0;
    private static int points30 = 30;
    private static int points40 = 40;
    private static int pointsAdvantage = 45;
    private int player1Ace = 0;
    private int player2Ace = 0;
    private static final String STATE_SCORE_1 = "player1Score";
    private static final String STATE_SCORE_2 = "player2Score";
    private static final String STATE_ACE_1 = "player1Ace";
    private static final String STATE_ACE_2 = "player2Ace";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(STATE_SCORE_1, player1Score);
        savedInstanceState.putInt(STATE_SCORE_2, player2Score);
        savedInstanceState.putInt(STATE_ACE_1, player1Ace);
        savedInstanceState.putInt(STATE_ACE_2, player2Ace);
// Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
        player1Score = savedInstanceState.getInt(STATE_SCORE_1);
        player2Score = savedInstanceState.getInt(STATE_SCORE_2);
        player1Ace = savedInstanceState.getInt(STATE_ACE_1);
        player2Ace = savedInstanceState.getInt(STATE_ACE_2);
        updateScore();
    }

    public void player1Score(View view) {
        if (player1Score == points30 && player2Score < points40) {
            player1Score = points40;
            updateScore();
        } else if (player1Score == points40 && player2Score < points40)
            announceWinner("Winner: Player 1");
        else if (player1Score == points30 && player2Score == points40) {
            deuce();
            player1Score = points40;
        } else if (player1Score == points40 && player2Score == points40) {
            advantage(1);
            player1Score += 5;
        } else if (player1Score == pointsAdvantage && player2Score == points40) {
            announceWinner("Winner: Player 1");
        } else if (player1Score == points40 && player2Score == pointsAdvantage) {
            deuce();
            player2Score -= 5;
        } else {
            player1Score += 15;
            updateScore();
        }
    }

    public void player2Score(View view) {
        if (player2Score == points30 && player1Score < points40) {
            player2Score = points40;
            updateScore();
        } else if (player2Score == points40 && player1Score < points40)
            announceWinner(getString(R.string.winnerPlayer1));
        else if (player2Score == points30 && player1Score == points40) {
            deuce();
            player2Score = points40;
        } else if (player2Score == points40 && player1Score == points40) {
            advantage(2);
            player2Score += 5;
        } else if (player2Score == pointsAdvantage && player1Score == points40) {
            announceWinner(getString(R.string.winnerPlayer2));
        } else if (player2Score == points40 && player1Score == pointsAdvantage) {
            deuce();
            player1Score -= 5;
        } else {
            player2Score += 15;
            updateScore();
        }
    }

    private void updateScore() {
        TextView player1TextView = (TextView) findViewById(R.id.player1PointsTextView);
        TextView player2TextView = (TextView) findViewById(R.id.player2PointsTextView);
        TextView player1AceTextView = (TextView) findViewById(R.id.player1AceTextView);
        TextView player2AceTextView = (TextView) findViewById(R.id.player2AceTextView);
        player1TextView.setText("" + player1Score);
        player2TextView.setText("" + player2Score);
        player1AceTextView.setText(getText(R.string.aceText).toString() + player1Ace);
        player2AceTextView.setText(getText(R.string.aceText).toString() + player2Ace);
    }

    private void announceWinner(String playerName) {
        TextView winnerTextView = (TextView) findViewById(R.id.winnerNameTextView);
        winnerTextView.setText(playerName);
    }

    private void deuce() {
        TextView player1TextView = (TextView) findViewById(R.id.player1PointsTextView);
        TextView player2TextView = (TextView) findViewById(R.id.player2PointsTextView);
        player1TextView.setText("D");
        player2TextView.setText("D");
    }

    private void advantage(int player) {
        TextView player1TextView = (TextView) findViewById(R.id.player1PointsTextView);
        TextView player2TextView = (TextView) findViewById(R.id.player2PointsTextView);
        if (player == 1) {
            player1TextView.setText("A");
            player2TextView.setText("-");
        } else if (player == 2) {
            player1TextView.setText("-");
            player2TextView.setText("A");
        }
    }

    public void resetScore(View view) {
        player1Score = 0;
        player2Score = 0;
        player1Ace = 0;
        player2Ace = 0;
        updateScore();
        announceWinner("");
    }

    public void player2Ace(View view) {
        player2Ace += 1;
        TextView player2AceView = (TextView) findViewById(R.id.player2AceTextView);
        player2AceView.setText(getText(R.string.aceText).toString() + player2Ace);
    }

    public void player1Ace(View view) {
        player1Ace += 1;
        TextView player1AceView = (TextView) findViewById(R.id.player1AceTextView);
        player1AceView.setText(getText(R.string.aceText).toString() + player1Ace);
    }
}
