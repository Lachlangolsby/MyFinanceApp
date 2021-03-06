package au.edu.unsw.infs3634.gamifiedlearning.SmartFinancialGoalSetting;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import au.edu.unsw.infs3634.gamifiedlearning.BadgesPage;
import au.edu.unsw.infs3634.gamifiedlearning.FinCalc;
import au.edu.unsw.infs3634.gamifiedlearning.HomePage;
import au.edu.unsw.infs3634.gamifiedlearning.Notes.NoteListActivity;
import au.edu.unsw.infs3634.gamifiedlearning.ProfileManagement;
import au.edu.unsw.infs3634.gamifiedlearning.QuizTopicSelection;
import au.edu.unsw.infs3634.gamifiedlearning.R;
import au.edu.unsw.infs3634.gamifiedlearning.SignUp.MainActivity;
import au.edu.unsw.infs3634.gamifiedlearning.SignUp.User;
import au.edu.unsw.infs3634.gamifiedlearning.SmartInvesting.SmartInvesting;

public class FinancialGoalSettingQuizLanding extends AppCompatActivity {
    // decalring variables to be used throughout class
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";
    private static final int REQUEST_CODE = 1;
    public static MediaPlayer mediaPlayer = null;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageView share;
    private TextView textViewHighscore;
    private String highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // loading correct layout
        setContentView(R.layout.activity_financial_goal_setting_quiz_landing);

        // starting gamified music
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.start();


        // logic behinde the pausing of music via the speaker image view
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

        // assigning share button to xml
        share = findViewById(R.id.ivShare);

        // declaring highscore text to relevant xml and retrieving through method
        textViewHighscore = findViewById(R.id.tvHighScore);
        loadHighscore();

        // declaring highscore text to relevant xml and onclick listener for start quiz button
        Button buttonStartQuiz = findViewById(R.id.btStart);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

        // assigning nav menu variables to xml of this page
        navigationView = findViewById(R.id.nav_View);
        drawerLayout = findViewById(R.id.tvTitle);

        //action when navigation menu open and close
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // on click listner that Uses intents to pass string through to other application on mobile device to share their learning progress in terms of high score attainment
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareMessage = "Just got a new HighScore on the Financial Goal Setting myfinance  quiz!!";
                Intent mSharingIntent = new Intent(Intent.ACTION_SEND);
                mSharingIntent.setType("Text/Plain");
                mSharingIntent.putExtra(Intent.EXTRA_SUBJECT, "MYFinance HighScore");
                mSharingIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(mSharingIntent, "Share Score Via"));
            }
        });

        //Navigation Menu Logic
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mProfile:
                        Toast.makeText(FinancialGoalSettingQuizLanding.this, "Profile page", Toast.LENGTH_SHORT);
                        Intent activityChangeIntent = new Intent(FinancialGoalSettingQuizLanding.this, ProfileManagement.class);
                        FinancialGoalSettingQuizLanding.this.startActivity(activityChangeIntent);
                        drawerLayout.closeDrawers();
                        stopAudio();
                        break;
                    case R.id.mHome:
                        Toast.makeText(FinancialGoalSettingQuizLanding.this, "Home", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentHome = new Intent(FinancialGoalSettingQuizLanding.this, HomePage.class);
                        FinancialGoalSettingQuizLanding.this.startActivity(activityChangeIntentHome);
                        drawerLayout.closeDrawers();
                        stopAudio();
                        break;
                    case R.id.mModule1:
                        Toast.makeText(FinancialGoalSettingQuizLanding.this, "Module1", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentCalculator = new Intent(FinancialGoalSettingQuizLanding.this, FinCalc.class);
                        FinancialGoalSettingQuizLanding.this.startActivity(activityChangeIntentCalculator);
                        drawerLayout.closeDrawers();
                        stopAudio();
                        break;
                    case R.id.mModule2:
                        Toast.makeText(FinancialGoalSettingQuizLanding.this, "Module2", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentSmartInvesting = new Intent(FinancialGoalSettingQuizLanding.this, SmartInvesting.class);
                        FinancialGoalSettingQuizLanding.this.startActivity(activityChangeIntentSmartInvesting);
                        drawerLayout.closeDrawers();
                        stopAudio();
                        break;
                    case R.id.mModule3:
                        Toast.makeText(FinancialGoalSettingQuizLanding.this, "Module3", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        Intent activityChangeIntentFG = new Intent(FinancialGoalSettingQuizLanding.this, FinancialGoalSetting.class);
                        FinancialGoalSettingQuizLanding.this.startActivity(activityChangeIntentFG);
                        stopAudio();
                        break;
                    case R.id.mModule4:
                        Toast.makeText(FinancialGoalSettingQuizLanding.this, "Module 4", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentQS = new Intent(FinancialGoalSettingQuizLanding.this, QuizTopicSelection.class);
                        FinancialGoalSettingQuizLanding.this.startActivity(activityChangeIntentQS);
                        drawerLayout.closeDrawers();
                        stopAudio();
                        break;
                    case R.id.mModule5:
                        Toast.makeText(FinancialGoalSettingQuizLanding.this, "Module 5", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentB = new Intent(FinancialGoalSettingQuizLanding.this, BadgesPage.class);
                        FinancialGoalSettingQuizLanding.this.startActivity(activityChangeIntentB);
                        drawerLayout.closeDrawers();
                        stopAudio();
                        break;
                    case R.id.mModule6:
                        Toast.makeText(FinancialGoalSettingQuizLanding.this, "Module 6", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentN = new Intent(FinancialGoalSettingQuizLanding.this, NoteListActivity.class);
                        FinancialGoalSettingQuizLanding.this.startActivity(activityChangeIntentN);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mShare:
                        String shareMessage = "Join MyFinance, it's fun and eductaional.";
                        Intent mSharingIntent = new Intent(Intent.ACTION_SEND);
                        mSharingIntent.setType("Text/Plain");
                        mSharingIntent.putExtra(Intent.EXTRA_SUBJECT, "MYFinance HighScore");
                        mSharingIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(mSharingIntent, "Share Score Via"));
                        break;
                    case R.id.mLogout:
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(FinancialGoalSettingQuizLanding.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                        Intent activityChangeIntent2 = new Intent(FinancialGoalSettingQuizLanding.this, MainActivity.class);
                        FinancialGoalSettingQuizLanding.this.startActivity(activityChangeIntent2);
                        drawerLayout.closeDrawers();
                        stopAudio();
                        break;
                }

                return false;
            }
        });
    }

    // Returning whether menu selected true or false
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // method for starting the quiz
    private void startQuiz() {
        Intent intent = new Intent(FinancialGoalSettingQuizLanding.this, FinancialGoalSettingQuiz.class);
        startActivityForResult(intent, REQUEST_CODE);
        stopAudio();
    }

    // loads highscore retrieved via firebase realtime db
    private void loadHighscore() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userid = user.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                // setting highScore to ui
                if (userProfile != null) {
                    String result = userProfile.FGSScore;
                    highscore = result;
                    textViewHighscore.setText("HighScore: " + highscore);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    // method to stop the audio player
    private void stopAudio() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}