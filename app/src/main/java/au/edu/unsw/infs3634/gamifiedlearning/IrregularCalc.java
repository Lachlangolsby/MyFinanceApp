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

public class IrregularCalc extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    private TextView mIRSolutionTitle,  mIRSolutionWeekly, mIRSolutionMonthly, mIRSolutionFortnightly;
    private EditText mEtExpenseType, mEtAmount, mETFrequency;
    private Button mBtnIrregularCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.irregular_calc);
        navigationView = findViewById(R.id.nav_View);
        drawerLayout = findViewById(R.id.irclayout);
        mIRSolutionTitle = findViewById(R.id.tvIrregularResultHint);
        mIRSolutionTitle.setVisibility(View.INVISIBLE);
        mIRSolutionWeekly = findViewById(R.id.tvSolutionWeekly);
        mIRSolutionWeekly.setVisibility(View.INVISIBLE);
        mIRSolutionMonthly = findViewById(R.id.tvSolutionMonthly);
        mIRSolutionMonthly.setVisibility(View.INVISIBLE);
        mIRSolutionFortnightly = findViewById(R.id.tvSolutionFortnightly);
        mIRSolutionFortnightly.setVisibility(View.INVISIBLE);
        mEtAmount = findViewById(R.id.etAmount);
        mETFrequency = findViewById(R.id.etFrequency);
        mEtExpenseType =findViewById(R.id.etExpenseType);
        mBtnIrregularCalc = findViewById(R.id.btnIrregularCalc);

        mBtnIrregularCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateIrregular();
            }
        });


        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mProfile:
                        Toast.makeText(IrregularCalc.this, "Profile page", Toast.LENGTH_SHORT);
                        Intent activityChangeIntent = new Intent(IrregularCalc.this, ProfileManagement.class);
                        IrregularCalc.this.startActivity(activityChangeIntent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mHome:
                        Toast.makeText(IrregularCalc.this, "Home", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentHome = new Intent(IrregularCalc.this, HomePage.class);
                        IrregularCalc.this.startActivity(activityChangeIntentHome);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule1:
                        Toast.makeText(IrregularCalc.this, "Module1", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentCalculator = new Intent(IrregularCalc.this, FinCalc.class);
                        IrregularCalc.this.startActivity(activityChangeIntentCalculator);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule2:
                        Toast.makeText(IrregularCalc.this, "Module2", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentSmartInvesting = new Intent(IrregularCalc.this, SmartInvesting.class);
                        IrregularCalc.this.startActivity(activityChangeIntentSmartInvesting);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule3:
                        Toast.makeText(IrregularCalc.this, "Module3", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        Intent activityChangeIntentFG = new Intent(IrregularCalc.this, FinancialGoalSetting.class);
                        IrregularCalc.this.startActivity(activityChangeIntentFG);
                        break;
                    case R.id.mModule4:
                        Toast.makeText(IrregularCalc.this, "Module 4", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentQS = new Intent(IrregularCalc.this, QuizTopicSelection.class);
                        IrregularCalc.this.startActivity(activityChangeIntentQS);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule5:
                        Toast.makeText(IrregularCalc.this, "Module 5", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentB = new Intent(IrregularCalc.this, BadgesPage.class);
                        IrregularCalc.this.startActivity(activityChangeIntentB);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule6:
                        Toast.makeText(IrregularCalc.this, "Module 6", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentN = new Intent(IrregularCalc.this, NoteListActivity.class);
                        IrregularCalc.this.startActivity(activityChangeIntentN);
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
                        Toast.makeText(IrregularCalc.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                        Intent activityChangeIntent2 = new Intent(IrregularCalc.this, MainActivity.class);
                        IrregularCalc.this.startActivity(activityChangeIntent2);
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
    public void generateIrregular(){
        String expenseType = mEtExpenseType.getText().toString();
        String Amount = mEtAmount.getText().toString();
        String Frequency = mETFrequency.getText().toString();
        DecimalFormat df = new DecimalFormat("#.##");

        if (expenseType.equals("")|| Amount.equals("") || Frequency.equals("")) {
            Toast.makeText(IrregularCalc.this, "Enter a Value for all fields", Toast.LENGTH_LONG).show();

        }else {
            mIRSolutionTitle.setText("Possible Saving Solutions for "+expenseType +" Expense:");
            int expenseTotal = Integer.parseInt(Amount)*Integer.parseInt(Frequency);
            double dailybreakdown = expenseTotal/365.000;
            double weekly = dailybreakdown*7;
            double fortnightly = dailybreakdown*14;
            double monthly = dailybreakdown *30.416667;

            mIRSolutionWeekly.setText("Save: $"+ df.format(weekly) + " Weekly, to cover yearly "+ expenseType + " Expense");
            mIRSolutionFortnightly.setText("Save: $"+ df.format(fortnightly) + " Fortnightly, to cover yearly "+ expenseType + " Expense");
            mIRSolutionMonthly.setText("Save: $"+ df.format(monthly) + " Monthly, to cover yearly "+ expenseType + " Expense");
            mIRSolutionTitle.setVisibility(View.VISIBLE);
            mIRSolutionWeekly.setVisibility(View.VISIBLE);
            mIRSolutionMonthly.setVisibility(View.VISIBLE);
            mIRSolutionFortnightly.setVisibility(View.VISIBLE);


        }

    }

    }
    

