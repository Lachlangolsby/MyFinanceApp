package au.edu.unsw.infs3634.gamifiedlearning.SmartInvesting;

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
import au.edu.unsw.infs3634.gamifiedlearning.SmartFinancialGoalSetting.FinancialGoalSetting;
import au.edu.unsw.infs3634.gamifiedlearning.HomePage;
import au.edu.unsw.infs3634.gamifiedlearning.SignUp.MainActivity;
import au.edu.unsw.infs3634.gamifiedlearning.Notes.NoteListActivity;
import au.edu.unsw.infs3634.gamifiedlearning.ProfileManagement;
import au.edu.unsw.infs3634.gamifiedlearning.QuizTopicSelection;
import au.edu.unsw.infs3634.gamifiedlearning.R;
import au.edu.unsw.infs3634.gamifiedlearning.SignUp.User;


public class SmartInvestingQuizLanding extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private TextView textViewHighscore;
    private String highscore;

    public static MediaPlayer mediaPlayer = null;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    private long backPressed;
    ImageView share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_investing_quiz_landing);
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.start();

        share = findViewById(R.id.ivShare2);

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
                stopAudio();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareMessage = "Just got a new HighScore on the SmartInvesting myfinance  quiz!!";
                Intent mSharingIntent = new Intent(Intent.ACTION_SEND);
                mSharingIntent.setType("Text/Plain");
                mSharingIntent.putExtra(Intent.EXTRA_SUBJECT, "MYFinance HighScore");
                mSharingIntent.putExtra(Intent.EXTRA_TEXT,shareMessage);
                startActivity(Intent.createChooser(mSharingIntent,"Share Score Via"));
            }
        });

        navigationView = findViewById(R.id.nav_View);
        drawerLayout = findViewById(R.id.tvTitle);

        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mProfile:
                        Toast.makeText(SmartInvestingQuizLanding.this, "Profile page", Toast.LENGTH_SHORT);
                        Intent activityChangeIntent = new Intent(SmartInvestingQuizLanding.this, ProfileManagement.class);
                        SmartInvestingQuizLanding.this.startActivity(activityChangeIntent);
                        drawerLayout.closeDrawers();
                        stopAudio();
                        break;
                    case R.id.mHome:
                        Toast.makeText(SmartInvestingQuizLanding.this, "Home", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentHome = new Intent(SmartInvestingQuizLanding.this, HomePage.class);
                        SmartInvestingQuizLanding.this.startActivity(activityChangeIntentHome);
                        drawerLayout.closeDrawers();
                        stopAudio();
                        break;
                    case R.id.mModule1:
                        Toast.makeText(SmartInvestingQuizLanding.this, "Module1", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentCalculator = new Intent(SmartInvestingQuizLanding.this, FinCalc.class);
                        SmartInvestingQuizLanding.this.startActivity(activityChangeIntentCalculator);
                        drawerLayout.closeDrawers();
                        stopAudio();
                        break;
                    case R.id.mModule2:
                        Toast.makeText(SmartInvestingQuizLanding.this, "Module2", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentSmartInvesting = new Intent(SmartInvestingQuizLanding.this, SmartInvesting.class);
                        SmartInvestingQuizLanding.this.startActivity(activityChangeIntentSmartInvesting);
                        drawerLayout.closeDrawers();
                        stopAudio();
                        break;
                    case R.id.mModule3:
                        Toast.makeText(SmartInvestingQuizLanding.this, "Module3", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        Intent activityChangeIntentFG = new Intent(SmartInvestingQuizLanding.this, FinancialGoalSetting.class);
                        SmartInvestingQuizLanding.this.startActivity(activityChangeIntentFG);
                        stopAudio();
                        break;
                    case R.id.mModule4:
                        Toast.makeText(SmartInvestingQuizLanding.this, "Module 4", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentQS = new Intent(SmartInvestingQuizLanding.this, QuizTopicSelection.class);
                        SmartInvestingQuizLanding.this.startActivity(activityChangeIntentQS);
                        drawerLayout.closeDrawers();
                        stopAudio();
                        break;
                    case R.id.mModule5:
                        Toast.makeText(SmartInvestingQuizLanding.this, "Module 5", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentB = new Intent(SmartInvestingQuizLanding.this, BadgesPage.class);
                        SmartInvestingQuizLanding.this.startActivity(activityChangeIntentB);
                        drawerLayout.closeDrawers();
                        stopAudio();
                        break;
                    case R.id.mModule6:
                        Toast.makeText(SmartInvestingQuizLanding.this, "Module 6", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentN = new Intent(SmartInvestingQuizLanding.this, NoteListActivity.class);
                        SmartInvestingQuizLanding.this.startActivity(activityChangeIntentN);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mShare:
                        String shareMessage = "Join MyFinance, it's fun and eductaional.";
                        Intent mSharingIntent = new Intent(Intent.ACTION_SEND);
                        mSharingIntent.setType("Text/Plain");
                        mSharingIntent.putExtra(Intent.EXTRA_SUBJECT, "MYFinance HighScore");
                        mSharingIntent.putExtra(Intent.EXTRA_TEXT,shareMessage);
                        startActivity(Intent.createChooser(mSharingIntent,"Share Score Via"));
                        break;
                    case R.id.mLogout:
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(SmartInvestingQuizLanding.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                        Intent activityChangeIntent2 = new Intent(SmartInvestingQuizLanding.this, MainActivity.class);
                       SmartInvestingQuizLanding.this.startActivity(activityChangeIntent2);
                        drawerLayout.closeDrawers();
                        stopAudio();
                        break;
                }

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void startQuiz() {
        Intent intent = new Intent(SmartInvestingQuizLanding.this, SmartInvestingQuiz.class);
        startActivityForResult(intent,REQUEST_CODE);
        finish();
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                int score = data.getIntExtra(SmartInvestingQuiz.EXTRA_SCORE, 0);
//                if (score > highscore) {
//                    updateHighscore(score);
//                }
//            }
//        }
//    }
    private void loadHighscore() {

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String userid =user.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile !=null){
                    String result = userProfile.SIScore;
                    highscore = result;
                    textViewHighscore.setText("HighScore: "+highscore);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void stopAudio() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
//    private void updateHighscore(int highscoreNew) {
//        highscore = highscoreNew;
//        textViewHighscore.setText("Highscore: " + highscore);
//        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putInt(KEY_HIGHSCORE, highscore);
//        editor.apply();
//    }

}

