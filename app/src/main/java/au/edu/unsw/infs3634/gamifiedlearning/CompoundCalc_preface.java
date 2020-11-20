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

public class CompoundCalc_preface extends AppCompatActivity {
    //putting all text/edit Views together aids readability
    private TextView mTvCompoundTitle;
    private TextView mTvCompoundPreface;
    private Button mBtnCompound;
    private ImageView mIvCompoundPreface;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ensure layout is from correct xml file
        setContentView(R.layout.compoundcalc_preface);

        mTvCompoundTitle = findViewById(R.id.tvCompoundTitle);
        //This does the actual preface text
        mTvCompoundPreface = findViewById(R.id.tvCompoundPreface);
        mBtnCompound = findViewById(R.id.btnCompound);
        mIvCompoundPreface = findViewById(R.id.ivCompoundPreface);
//Configure/install textviews, drawers and buttons
        mBtnCompound.setOnClickListener
                (new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CompoundCalc("Compound Calculator"); }
                });
        navigationView = findViewById(R.id.nav_View);
        drawerLayout = findViewById(R.id.cilayout);

        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//Navigation menu code
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mProfile:
                        Toast.makeText(CompoundCalc_preface.this, "Profile page", Toast.LENGTH_SHORT);
                        Intent activityChangeIntent = new Intent(CompoundCalc_preface.this, ProfileManagement.class);
                        CompoundCalc_preface.this.startActivity(activityChangeIntent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mHome:
                        Toast.makeText(CompoundCalc_preface.this, "Home", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentHome = new Intent(CompoundCalc_preface.this, HomePage.class);
                        CompoundCalc_preface.this.startActivity(activityChangeIntentHome);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule1:
                        Toast.makeText(CompoundCalc_preface.this, "Module1", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentCalculator = new Intent(CompoundCalc_preface.this, FinCalc.class);
                        CompoundCalc_preface.this.startActivity(activityChangeIntentCalculator);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule2:
                        Toast.makeText(CompoundCalc_preface.this, "Module2", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentSmartInvesting = new Intent(CompoundCalc_preface.this, SmartInvesting.class);
                        CompoundCalc_preface.this.startActivity(activityChangeIntentSmartInvesting);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule3:
                        Toast.makeText(CompoundCalc_preface.this, "Module3", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        Intent activityChangeIntentFG = new Intent(CompoundCalc_preface.this, FinancialGoalSetting.class);
                        CompoundCalc_preface.this.startActivity(activityChangeIntentFG);
                        break;
                    case R.id.mModule4:
                        Toast.makeText(CompoundCalc_preface.this, "Module 4", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentQS = new Intent(CompoundCalc_preface.this, QuizTopicSelection.class);
                        CompoundCalc_preface.this.startActivity(activityChangeIntentQS);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule5:
                        Toast.makeText(CompoundCalc_preface.this, "Module 5", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentB = new Intent(CompoundCalc_preface.this, BadgesPage.class);
                        CompoundCalc_preface.this.startActivity(activityChangeIntentB);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule6:
                        Toast.makeText(CompoundCalc_preface.this, "Module 6", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentN = new Intent(CompoundCalc_preface.this, NoteListActivity.class);
                        CompoundCalc_preface.this.startActivity(activityChangeIntentN);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mLogout:
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(CompoundCalc_preface.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                        Intent activityChangeIntent2 = new Intent(CompoundCalc_preface.this, MainActivity.class);
                        CompoundCalc_preface.this.startActivity(activityChangeIntent2);
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
    //This goes to the actual calculator
    private void CompoundCalc(String message) {
        Intent intent = new Intent(au.edu.unsw.infs3634.gamifiedlearning.CompoundCalc_preface.this, CompoundCalc.class);
        startActivity(intent);
    }
}
