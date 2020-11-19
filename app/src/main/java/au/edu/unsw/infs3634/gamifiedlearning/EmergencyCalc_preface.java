package au.edu.unsw.infs3634.gamifiedlearning;

import android.content.Intent;
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

import au.edu.unsw.infs3634.gamifiedlearning.Notes.NoteListActivity;
import au.edu.unsw.infs3634.gamifiedlearning.SignUp.MainActivity;
import au.edu.unsw.infs3634.gamifiedlearning.SmartFinancialGoalSetting.FinancialGoalSetting;
import au.edu.unsw.infs3634.gamifiedlearning.SmartInvesting.SmartInvesting;

public class EmergencyCalc_preface extends AppCompatActivity {

    private TextView mTvEmergencyTitle;
    private TextView mTvEmergencyCalcPreface;
    private Button mBtnEmergency;
    private ImageView mIvEmergencyPreface;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergencycalc_preface);

        mTvEmergencyTitle = findViewById(R.id.tvEmergencyTitle);
        mTvEmergencyCalcPreface = findViewById(R.id.tvEmergencyCalcPreface);
        mBtnEmergency = findViewById(R.id.btnEmergency);
        mIvEmergencyPreface = findViewById(R.id.ivEmergencyPreface);

        mBtnEmergency.setOnClickListener
                (new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EmergencyCalc("Emergency Fund"); }
                });
        navigationView = findViewById(R.id.nav_View);
        drawerLayout = findViewById(R.id.eflayout);

        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mProfile:
                        Toast.makeText(EmergencyCalc_preface.this, "Profile page", Toast.LENGTH_SHORT);
                        Intent activityChangeIntent = new Intent(EmergencyCalc_preface.this, ProfileManagement.class);
                        EmergencyCalc_preface.this.startActivity(activityChangeIntent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mHome:
                        Toast.makeText(EmergencyCalc_preface.this, "Home", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentHome = new Intent(EmergencyCalc_preface.this, HomePage.class);
                        EmergencyCalc_preface.this.startActivity(activityChangeIntentHome);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule1:
                        Toast.makeText(EmergencyCalc_preface.this, "Module1", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentCalculator = new Intent(EmergencyCalc_preface.this, FinCalc.class);
                        EmergencyCalc_preface.this.startActivity(activityChangeIntentCalculator);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule2:
                        Toast.makeText(EmergencyCalc_preface.this, "Module2", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentSmartInvesting = new Intent(EmergencyCalc_preface.this, SmartInvesting.class);
                        EmergencyCalc_preface.this.startActivity(activityChangeIntentSmartInvesting);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule3:
                        Toast.makeText(EmergencyCalc_preface.this, "Module3", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        Intent activityChangeIntentFG = new Intent(EmergencyCalc_preface.this, FinancialGoalSetting.class);
                        EmergencyCalc_preface.this.startActivity(activityChangeIntentFG);
                        break;
                    case R.id.mModule4:
                        Toast.makeText(EmergencyCalc_preface.this, "Module 4", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentQS = new Intent(EmergencyCalc_preface.this, QuizTopicSelection.class);
                        EmergencyCalc_preface.this.startActivity(activityChangeIntentQS);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule5:
                        Toast.makeText(EmergencyCalc_preface.this, "Module 5", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentB = new Intent(EmergencyCalc_preface.this, BadgesPage.class);
                        EmergencyCalc_preface.this.startActivity(activityChangeIntentB);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule6:
                        Toast.makeText(EmergencyCalc_preface.this, "Module 6", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentN = new Intent(EmergencyCalc_preface.this, NoteListActivity.class);
                        EmergencyCalc_preface.this.startActivity(activityChangeIntentN);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mLogout:
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(EmergencyCalc_preface.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                        Intent activityChangeIntent2 = new Intent(EmergencyCalc_preface.this, MainActivity.class);
                        EmergencyCalc_preface.this.startActivity(activityChangeIntent2);
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


    private void EmergencyCalc(String message) {
        Intent intent = new Intent(au.edu.unsw.infs3634.gamifiedlearning.EmergencyCalc_preface.this, au.edu.unsw.infs3634.gamifiedlearning.EmergencyCalc.class);
        startActivity(intent);
    }
}
