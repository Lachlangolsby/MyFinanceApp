package au.edu.unsw.infs3634.gamifiedlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import au.edu.unsw.infs3634.gamifiedlearning.Notes.NoteListActivity;
import au.edu.unsw.infs3634.gamifiedlearning.SignUp.MainActivity;
import au.edu.unsw.infs3634.gamifiedlearning.SmartFinancialGoalSetting.FinancialGoalSetting;
import au.edu.unsw.infs3634.gamifiedlearning.SmartFinancialGoalSetting.FinancialGoalSettingQuizLanding;
import au.edu.unsw.infs3634.gamifiedlearning.SmartInvesting.SmartInvesting;
import au.edu.unsw.infs3634.gamifiedlearning.SmartInvesting.SmartInvestingQuizLanding;

public class QuizTopicSelection extends AppCompatActivity {
    ImageView si, fg;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_topic_selection);
        si = findViewById(R.id.ivSI);
        fg = findViewById(R.id.ivFG);

        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityChange = new Intent(QuizTopicSelection.this, SmartInvestingQuizLanding.class);
                QuizTopicSelection.this.startActivity(activityChange);

            }
        });
        fg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityChange = new Intent(QuizTopicSelection.this, FinancialGoalSettingQuizLanding.class);
                QuizTopicSelection.this.startActivity(activityChange);
            }
        });
        navigationView = findViewById(R.id.nav_View);
        drawerLayout = findViewById(R.id.QuizSelectionLayout);

        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mProfile:
                        Toast.makeText(QuizTopicSelection.this, "Profile page", Toast.LENGTH_SHORT);
                        Intent activityChangeIntent = new Intent(QuizTopicSelection.this, ProfileManagement.class);
                        QuizTopicSelection.this.startActivity(activityChangeIntent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mHome:
                        Toast.makeText(QuizTopicSelection.this, "Home", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentHome = new Intent(QuizTopicSelection.this, HomePage.class);
                        QuizTopicSelection.this.startActivity(activityChangeIntentHome);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule1:
                        Toast.makeText(QuizTopicSelection.this, "Module1", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentCalculator = new Intent(QuizTopicSelection.this, FinCalc.class);
                        QuizTopicSelection.this.startActivity(activityChangeIntentCalculator);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule2:
                        Toast.makeText(QuizTopicSelection.this, "Module2", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentSmartInvesting = new Intent(QuizTopicSelection.this, SmartInvesting.class);
                        QuizTopicSelection.this.startActivity(activityChangeIntentSmartInvesting);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule3:
                        Toast.makeText(QuizTopicSelection.this, "Module3", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        Intent activityChangeIntentFG = new Intent(QuizTopicSelection.this, FinancialGoalSetting.class);
                       QuizTopicSelection.this.startActivity(activityChangeIntentFG);
                        break;
                    case R.id.mModule4:
                        Toast.makeText(QuizTopicSelection.this, "Module 4", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentQS = new Intent(QuizTopicSelection.this, QuizTopicSelection.class);
                        QuizTopicSelection.this.startActivity(activityChangeIntentQS);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule5:
                        Toast.makeText(QuizTopicSelection.this, "Module 5", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentB = new Intent(QuizTopicSelection.this, BadgesPage.class);
                        QuizTopicSelection.this.startActivity(activityChangeIntentB);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule6:
                        Toast.makeText(QuizTopicSelection.this, "Module 6", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentN = new Intent(QuizTopicSelection.this, NoteListActivity.class);
                        QuizTopicSelection.this.startActivity(activityChangeIntentN);
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
                    Toast.makeText(QuizTopicSelection.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                        Intent activityChangeIntent2 = new Intent(QuizTopicSelection.this, MainActivity.class);
                        QuizTopicSelection.this.startActivity(activityChangeIntent2);
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


}
