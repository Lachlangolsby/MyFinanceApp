package au.edu.unsw.infs3634.gamifiedlearning.SmartFinancialGoalSetting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import au.edu.unsw.infs3634.gamifiedlearning.R;
import au.edu.unsw.infs3634.gamifiedlearning.SignUp.User;

import static au.edu.unsw.infs3634.gamifiedlearning.SmartInvesting.SmartInvestingQuizLanding.mediaPlayer;

public class FinancialGoalSettingQuiz extends AppCompatActivity {
    public static final long CountDown = 30000;


    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView tvCountDown;

    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;
    private ColorStateList textColorDefaultRb;


    private  ColorStateList textColorDefaultCount;
    private CountDownTimer countDownTimer;
    private long timeleft;



    private List<FGQuizQuestions> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private FGQuizQuestions currentQuestion;
    private int score;
    private boolean answered;

    private long backPressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_goal_setting_quiz);

        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.start();
        final ImageView sound = findViewById(R.id.sound2);
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

        textViewQuestion = findViewById(R.id.tvQuestion);
        textViewScore = findViewById(R.id.tvScore);
        textViewQuestionCount = findViewById(R.id.tvQuestionCount);

        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.rbA);
        rb2 = findViewById(R.id.rbB);
        rb3 = findViewById(R.id.rbC);
        rb4 = findViewById(R.id.rbD);
        tvCountDown = findViewById(R.id.tvCountDown2);
        buttonConfirmNext = findViewById(R.id.btCOnfirm);
        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCount = tvCountDown.getTextColors();


        FGQuizDBHelper dbHelper = new FGQuizDBHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()|| rb4.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(FinancialGoalSettingQuiz.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }
    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb4.setTextColor(textColorDefaultRb);

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());
            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");

            timeleft = CountDown;
            CountDownStart();
        } else {
            finishQuiz();
            stopAudio();
        }
    }

    private void CountDownStart() {
        countDownTimer = new CountDownTimer(timeleft,1000) {
            @Override
            public void onTick(long l) {
                timeleft = l;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                timeleft =0;
                updateCountDownText();
                checkAnswer();

            }
        }.start();

    }

    private void updateCountDownText() {
        int seconds = (int) (timeleft/1000);

        String Time = String.format(Locale.getDefault(), "%02d", seconds);
        tvCountDown.setText(Time);

        if (timeleft<10000){
            tvCountDown.setTextColor(Color.RED);
        }else {
            tvCountDown.setTextColor(textColorDefaultCount);
        }
    }

    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;
        if (answerNr == currentQuestion.getAnswerNr()) {
            score++;
            textViewScore.setText("Score: " + score);
        }
        showSolution();
    }
    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);
        switch (currentQuestion.getAnswerNr()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 3 is correct");
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 4 is correct");
                break;
        }
        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Next");
        } else {
            buttonConfirmNext.setText("Finish");
        }
    }

    public void finishQuiz() {

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        final String userid =user.getUid();
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                String result = userProfile.FGSScore;
                if (score > Integer.parseInt(result) ){
                    Toast.makeText(FinancialGoalSettingQuiz.this, "Quiz Complete", Toast.LENGTH_SHORT).show();
                    Toast.makeText(FinancialGoalSettingQuiz.this, "Congratulations new highScore", Toast.LENGTH_LONG).show();
                    reference.child(userid).child("FGSScore").setValue(String.valueOf(score));
                }else {
                    FirebaseDatabase.getInstance().getReference("Users").child("FGSScore").setValue(result);
                    Toast.makeText(FinancialGoalSettingQuiz.this, "Quiz Complete", Toast.LENGTH_LONG).show();

                }
                Intent activityChangeIntentQS = new Intent(FinancialGoalSettingQuiz.this, FinancialGoalSettingQuizLanding.class);
                FinancialGoalSettingQuiz.this.startActivity(activityChangeIntentQS);
                finish();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @Override
    public void onBackPressed() {
        if (backPressed + 2000 > System.currentTimeMillis()) {
            finishQuiz();
            stopAudio();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }
        backPressed = System.currentTimeMillis();
    }

    private void stopAudio() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
