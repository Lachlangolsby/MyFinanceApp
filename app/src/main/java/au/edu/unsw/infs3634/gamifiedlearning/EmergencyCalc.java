package au.edu.unsw.infs3634.gamifiedlearning;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DecimalFormat;

import au.edu.unsw.infs3634.gamifiedlearning.Notes.NoteListActivity;
import au.edu.unsw.infs3634.gamifiedlearning.SignUp.MainActivity;
import au.edu.unsw.infs3634.gamifiedlearning.SmartFinancialGoalSetting.FinancialGoalSetting;
import au.edu.unsw.infs3634.gamifiedlearning.SmartInvesting.SmartInvesting;

public class EmergencyCalc extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    EditText mCarExpense, mBills, mFHC, mMisc;
    TextView mWeekly, mFortnightly, mMonthly, mEmergencyfundTitle;
    Button btGenerate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_calc);


        mBills = findViewById(R.id.etBillExpense);
        mCarExpense = findViewById(R.id.etCarExpense);
        mFHC = findViewById(R.id.etFHCExpense);
        mMisc = findViewById(R.id.etMiscelaneousExpenses);
        mWeekly = findViewById(R.id.tvWeeklySaving);
        mWeekly.setVisibility(View.INVISIBLE);
        mFortnightly = findViewById(R.id.tvFortnightlySaving);
        mFortnightly.setVisibility(View.INVISIBLE);
        mMonthly =findViewById(R.id.tvMonthlySaving);
        mMonthly.setVisibility(View.INVISIBLE);
        btGenerate = findViewById(R.id.btEmergencyFund);
        mEmergencyfundTitle = findViewById(R.id.tvEmergencyFund);
        mEmergencyfundTitle.setVisibility(View.INVISIBLE);

        btGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GenerateSavingsPlan();

            }
        });



        navigationView = findViewById(R.id.nav_View);
        drawerLayout = findViewById(R.id.efcLayout);

        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mProfile:
                        Toast.makeText(EmergencyCalc.this, "Profile page", Toast.LENGTH_SHORT);
                        Intent activityChangeIntent = new Intent(EmergencyCalc.this, ProfileManagement.class);
                        EmergencyCalc.this.startActivity(activityChangeIntent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mHome:
                        Toast.makeText(EmergencyCalc.this, "Home", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentHome = new Intent(EmergencyCalc.this, HomePage.class);
                        EmergencyCalc.this.startActivity(activityChangeIntentHome);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule1:
                        Toast.makeText(EmergencyCalc.this, "Module1", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentCalculator = new Intent(EmergencyCalc.this, FinCalc.class);
                        EmergencyCalc.this.startActivity(activityChangeIntentCalculator);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule2:
                        Toast.makeText(EmergencyCalc.this, "Module2", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentSmartInvesting = new Intent(EmergencyCalc.this, SmartInvesting.class);
                        EmergencyCalc.this.startActivity(activityChangeIntentSmartInvesting);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule3:
                        Toast.makeText(EmergencyCalc.this, "Module3", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        Intent activityChangeIntentFG = new Intent(EmergencyCalc.this, FinancialGoalSetting.class);
                        EmergencyCalc.this.startActivity(activityChangeIntentFG);
                        break;
                    case R.id.mModule4:
                        Toast.makeText(EmergencyCalc.this, "Module 4", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentQS = new Intent(EmergencyCalc.this, QuizTopicSelection.class);
                        EmergencyCalc.this.startActivity(activityChangeIntentQS);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule5:
                        Toast.makeText(EmergencyCalc.this, "Module 5", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentB = new Intent(EmergencyCalc.this, BadgesPage.class);
                        EmergencyCalc.this.startActivity(activityChangeIntentB);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule6:
                        Toast.makeText(EmergencyCalc.this, "Module 6", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentN = new Intent(EmergencyCalc.this, NoteListActivity.class);
                        EmergencyCalc.this.startActivity(activityChangeIntentN);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mLogout:
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(EmergencyCalc.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                        Intent activityChangeIntent2 = new Intent(EmergencyCalc.this, MainActivity.class);
                        EmergencyCalc.this.startActivity(activityChangeIntent2);
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
    @SuppressLint("SetTextI18n")
    public void GenerateSavingsPlan(){

        String bills = mBills.getText().toString();
        String car = mCarExpense.getText().toString();
        String FHC = mFHC.getText().toString();
        String Misc = mMisc.getText().toString();
        DecimalFormat df = new DecimalFormat("#.##");
        if (bills.equals("")|| car.equals("") || FHC.equals("") || Misc.equals("")) {
            Toast.makeText(EmergencyCalc.this, "Enter a Value for all fields", Toast.LENGTH_LONG).show();

        }else {
            double dailyExpense = ((Double.parseDouble(bills)+Double.parseDouble(car)+Double.parseDouble(FHC)+Double.parseDouble(Misc))/7);
            double weeklyExpense = dailyExpense*7;
            double fortnightlyExpense = dailyExpense *14;
            double monthlyExpense = dailyExpense*30;
            double savingPerWeek = weeklyExpense/365;
            double savingPerFort = fortnightlyExpense/365;
            double savingPerMonth = monthlyExpense/365;

            mWeekly.setText("Save: $"+ df.format(savingPerWeek)+ " per day for one year to cover one weeks expenses");
            mFortnightly.setText("Save: $"+ df.format(savingPerFort)+ " per day for one year to cover one Fortnights expenses");
            mMonthly.setText("Save: $"+ df.format(savingPerMonth)+ " per day for one year to cover one Months expenses");
            mWeekly.setVisibility(View.VISIBLE);
            mMonthly.setVisibility(View.VISIBLE);
            mFortnightly.setVisibility(View.VISIBLE);
            mEmergencyfundTitle.setVisibility(View.VISIBLE);

        }
    }


}



