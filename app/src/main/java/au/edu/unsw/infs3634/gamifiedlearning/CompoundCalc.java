package au.edu.unsw.infs3634.gamifiedlearning;

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

public class CompoundCalc extends AppCompatActivity {

    //putting all text/edit Views together aids readability
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    private TextView mTvCompoundTitle2, mTvInvestAmount, mTvInterestRate, mTvCompoundingInterval, mTvMature, mTvExpectedReturn, mTvExpectedReturnCompound;
    private EditText mEditTextLumpSum, mEditTextInterestRate, mEditTextCompoundInterval, mEditTextMaturity;
    private Button mBtnCompoundCalc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ensure layout is from correct xml file
        setContentView(R.layout.compound_calc);
//Configure/install textviews, drawers and buttons
        mTvExpectedReturn = findViewById(R.id.tvExpectedReturn);
        mTvExpectedReturn.setVisibility(View.INVISIBLE);
        mTvInterestRate = findViewById(R.id.tvInterestRate);
        mTvCompoundingInterval = findViewById(R.id.tvCompoundingInterval);
        mTvExpectedReturn = findViewById(R.id.tvExpectedReturn);
        mTvExpectedReturnCompound = findViewById(R.id.tvExpectedReturnCompound);
        mEditTextLumpSum = findViewById(R.id.editTextLumpSum);
        mEditTextInterestRate = findViewById(R.id.editTextInterestRate);
        mEditTextCompoundInterval = findViewById(R.id.editTextCompoundInterval);
        mEditTextMaturity = findViewById(R.id.editTextMaturity);
        mBtnCompoundCalc = findViewById(R.id.btnCompoundCalc);

        mBtnCompoundCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GenerateCompoundedAmount();


            }
        });



        navigationView = findViewById(R.id.nav_View);
        drawerLayout = findViewById(R.id.cicLayout);

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
                        Toast.makeText(CompoundCalc.this, "Profile page", Toast.LENGTH_SHORT);
                        Intent activityChangeIntent = new Intent(CompoundCalc.this, ProfileManagement.class);
                        CompoundCalc.this.startActivity(activityChangeIntent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mHome:
                        Toast.makeText(CompoundCalc.this, "Home", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentHome = new Intent(CompoundCalc.this, HomePage.class);
                        CompoundCalc.this.startActivity(activityChangeIntentHome);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule1:
                        Toast.makeText(CompoundCalc.this, "Module1", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentCalculator = new Intent(CompoundCalc.this, FinCalc.class);
                        CompoundCalc.this.startActivity(activityChangeIntentCalculator);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule2:
                        Toast.makeText(CompoundCalc.this, "Module2", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentSmartInvesting = new Intent(CompoundCalc.this, SmartInvesting.class);
                        CompoundCalc.this.startActivity(activityChangeIntentSmartInvesting);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule3:
                        Toast.makeText(CompoundCalc.this, "Module3", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        Intent activityChangeIntentFG = new Intent(CompoundCalc.this, FinancialGoalSetting.class);
                        CompoundCalc.this.startActivity(activityChangeIntentFG);
                        break;
                    case R.id.mModule4:
                        Toast.makeText(CompoundCalc.this, "Module 4", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentQS = new Intent(CompoundCalc.this, QuizTopicSelection.class);
                        CompoundCalc.this.startActivity(activityChangeIntentQS);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule5:
                        Toast.makeText(CompoundCalc.this, "Module 5", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentB = new Intent(CompoundCalc.this, BadgesPage.class);
                        CompoundCalc.this.startActivity(activityChangeIntentB);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule6:
                        Toast.makeText(CompoundCalc.this, "Module 6", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentN = new Intent(CompoundCalc.this, NoteListActivity.class);
                        CompoundCalc.this.startActivity(activityChangeIntentN);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mLogout:
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(CompoundCalc.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                        Intent activityChangeIntent2 = new Intent(CompoundCalc.this, MainActivity.class);
                        CompoundCalc.this.startActivity(activityChangeIntent2);
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


    public void GenerateCompoundedAmount(){
        double Amount = 0;
        double AmountPowered = 0;
        DecimalFormat df = new DecimalFormat("#.##");
        String lumpSum = mEditTextLumpSum.getText().toString();
        String interestRate = mEditTextInterestRate.getText().toString();
        String compoundInterval = mEditTextCompoundInterval.getText().toString();
        String Maturity = mEditTextMaturity.getText().toString();
        if (lumpSum.equals("")|| interestRate.equals("") || compoundInterval.equals("") || Maturity.equals("")) {
        Toast.makeText(CompoundCalc.this, "Enter a Value for all fields", Toast.LENGTH_LONG).show();

        }else {
            AmountPowered = (1 + ((Double.parseDouble(interestRate)) / 100)/Integer.parseInt(compoundInterval));
            Amount = Integer.parseInt(lumpSum) * Math.pow(AmountPowered, (Double.parseDouble(Maturity)*Double.parseDouble(compoundInterval)));
            mTvExpectedReturnCompound.setText("$ " + df.format(Amount));
            mTvExpectedReturn.setVisibility(View.VISIBLE);

            System.out.println(Amount);
            System.out.println(AmountPowered);
        }
        }

}
