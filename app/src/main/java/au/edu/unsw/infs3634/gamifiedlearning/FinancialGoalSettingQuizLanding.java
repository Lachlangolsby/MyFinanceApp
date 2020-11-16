package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FinancialGoalSettingQuizLanding extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private TextView textViewHighscore;
    private int highscore;

    public static MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_goal_setting_quiz_landing);
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.start();

        final ImageView sound = findViewById(R.id.sound);
        sound.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    sound.setColorFilter(Color.argb(255, 127, 255, 0));
                } else {
                    mediaPlayer.start();
                    sound.setColorFilter(Color.argb(255, 255, 0, 0));
                }
            }
        });

        textViewHighscore = findViewById(R.id.tvHighScore);
        loadHighscore();

        Button buttonStartQuiz = findViewById(R.id.btStart);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }
    private void startQuiz() {
        Intent intent = new Intent(FinancialGoalSettingQuizLanding.this, FinancialGoalSettingQuiz.class);
        startActivityForResult(intent,REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(FinancialGoalSettingQuiz.EXTRA_SCORE, 0);
                if (score > highscore) {
                    updateHighscore(score);
                }
            }
        }
    }
    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighscore.setText("Highscore: " + highscore);
    }
    private void updateHighscore(int highscoreNew) {
        highscore = highscoreNew;
        textViewHighscore.setText("Highscore: " + highscore);
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }
}