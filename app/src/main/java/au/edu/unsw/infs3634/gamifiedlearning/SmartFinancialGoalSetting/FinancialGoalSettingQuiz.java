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

    // declaring variables to be used throughout the class
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


    private ColorStateList textColorDefaultCount;
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
        // loading correct xml layout
        setContentView(R.layout.activity_financial_goal_setting_quiz);

        // creating gamified music player to be started
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.start();

        // assigning button to xml and onclick listener to pause music
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


        // Assigning variables to xml items
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


        // creating an SQLdb helper class item
        FGQuizDBHelper dbHelper = new FGQuizDBHelper(this);
        // retrieving questions using dbhelper get questions method
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        // shuffling questions
        Collections.shuffle(questionList);

        // calling method to get next question
        showNextQuestion();

        // onclick listener for logic behind mcq choice
        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                        // checking answer
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

    // this method is for showing the next question
    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb4.setTextColor(textColorDefaultRb);

        // changes ui views to next question should it not be the last
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

            // getting time left and calling countdown timer
            timeleft = CountDown;
            CountDownStart();
        } else {
            // if last question  finish quiz
            finishQuiz();
            stopAudio();
        }
    }

    // method starts countdown timer
    private void CountDownStart() {
        countDownTimer = new CountDownTimer(timeleft, 1000) {
            @Override
            public void onTick(long l) {
                timeleft = l;
                updateCountDownText();

            }

            // method provides logic behind steps if timer runs out
            @Override
            public void onFinish() {
                timeleft = 0;
                updateCountDownText();
                checkAnswer();

            }
            // starts timer
        }.start();

    }

    // method displays timer to user
    private void updateCountDownText() {
        int seconds = (int) (timeleft / 1000);


        // sets text on Ui to timer and counts down
        String Time = String.format(Locale.getDefault(), "%02d", seconds);
        tvCountDown.setText(Time);

        // if time left less than 10 seconds make color red
        if (timeleft < 10000) {
            tvCountDown.setTextColor(Color.RED);
        } else {
            tvCountDown.setTextColor(textColorDefaultCount);
        }
    }

    // logic behind checking answer
    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;
        // checking if answer selected matches correct answer in db
        if (answerNr == currentQuestion.getAnswerNr()) {
            score++;
            textViewScore.setText("Score: " + score);
        }
        // displaying results to user
        showSolution();
    }

    // showing solution to user
    private void showSolution() {
        // setting ui elements to red unless correct answer
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
        // button changing whether last question or not
        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Next");
        } else {
            buttonConfirmNext.setText("Finish");
        }
    }

    // method retrieves highscore from firebase and compares to score in quiz either storing or rejecting result in firebase
    public void finishQuiz() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String userid = user.getUid();
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                String result = userProfile.FGSScore;
                if (score > Integer.parseInt(result)) {
                    Toast.makeText(FinancialGoalSettingQuiz.this, "Congratulations new highScore", Toast.LENGTH_LONG).show();
                    reference.child(userid).child("FGSScore").setValue(String.valueOf(score));
                } else {
                    FirebaseDatabase.getInstance().getReference("Users").child("FGSScore").setValue(result);
                    Toast.makeText(FinancialGoalSettingQuiz.this, "Quiz Complete", Toast.LENGTH_LONG).show();

                }// finishes activity
                Intent activityChangeIntentQS = new Intent(FinancialGoalSettingQuiz.this, FinancialGoalSettingQuizLanding.class);
                FinancialGoalSettingQuiz.this.startActivity(activityChangeIntentQS);
                finish();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    // stop user exiting the quiz on accident with the back button on their phone
    @Override
    public void onBackPressed() {
        // allowing them to exit if button pushed twice in quick succession
        if (backPressed + 2000 > System.currentTimeMillis()) {
            finishQuiz();
            // stop audio method
            stopAudio();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }
        backPressed = System.currentTimeMillis();
    }

    // to end countdown timer when activity done with
    private void stopAudio() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
