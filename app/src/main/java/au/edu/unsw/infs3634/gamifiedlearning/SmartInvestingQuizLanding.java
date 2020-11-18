package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_investing_quiz_landing);
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
                        break;
                    case R.id.mHome:
                        Toast.makeText(SmartInvestingQuizLanding.this, "Home", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentHome = new Intent(SmartInvestingQuizLanding.this, HomePage.class);
                        SmartInvestingQuizLanding.this.startActivity(activityChangeIntentHome);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule1:
                        Toast.makeText(SmartInvestingQuizLanding.this, "Module1", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentCalculator = new Intent(SmartInvestingQuizLanding.this, SmartInvesting.class);
                        SmartInvestingQuizLanding.this.startActivity(activityChangeIntentCalculator);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule2:
                        Toast.makeText(SmartInvestingQuizLanding.this, "Module2", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentSmartInvesting = new Intent(SmartInvestingQuizLanding.this, SmartInvesting.class);
                        SmartInvestingQuizLanding.this.startActivity(activityChangeIntentSmartInvesting);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule3:
                        Toast.makeText(SmartInvestingQuizLanding.this, "Module3", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        Intent activityChangeIntentFG = new Intent(SmartInvestingQuizLanding.this, FinancialGoalSetting.class);
                        SmartInvestingQuizLanding.this.startActivity(activityChangeIntentFG);
                        break;
                    case R.id.mModule4:
                        Toast.makeText(SmartInvestingQuizLanding.this, "Module 4", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentQS = new Intent(SmartInvestingQuizLanding.this, QuizTopicSelection.class);
                        SmartInvestingQuizLanding.this.startActivity(activityChangeIntentQS);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule5:
                        Toast.makeText(SmartInvestingQuizLanding.this, "Module 5", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentB = new Intent(SmartInvestingQuizLanding.this, QuizTopicSelection.class);
                        SmartInvestingQuizLanding.this.startActivity(activityChangeIntentB);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mLogout:
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(SmartInvestingQuizLanding.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                        Intent activityChangeIntent2 = new Intent(SmartInvestingQuizLanding.this, MainActivity.class);
                       SmartInvestingQuizLanding.this.startActivity(activityChangeIntent2);
                        drawerLayout.closeDrawers();
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
//    private void updateHighscore(int highscoreNew) {
//        highscore = highscoreNew;
//        textViewHighscore.setText("Highscore: " + highscore);
//        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putInt(KEY_HIGHSCORE, highscore);
//        editor.apply();
//    }
}

