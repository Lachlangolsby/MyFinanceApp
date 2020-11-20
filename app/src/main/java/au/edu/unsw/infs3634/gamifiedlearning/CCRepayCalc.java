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

public class CCRepayCalc extends AppCompatActivity {

    //putting all text/edit Views together aids readability
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    EditText mOutstandingBalance, mFees, mInterestRate;
    TextView mSolutionTitle, mMinRepayment, mMonthly,mThreeMonthly,mYearly;
    Button btCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ensure layout is from correct xml file
        setContentView(R.layout.ccrepay_calc);
        //Configure/install textviews, drawers and buttons
        mOutstandingBalance = findViewById(R.id.etOutstandingBalance);
        mFees=findViewById(R.id.etAnnualFees);
        mInterestRate = findViewById(R.id.etAnnualInterestRate);
        mSolutionTitle = findViewById(R.id.tvCCRSolutionTitle);
        mSolutionTitle.setVisibility(View.INVISIBLE);
        mMinRepayment = findViewById(R.id.tvCCRMin);
        mMinRepayment.setVisibility(View.INVISIBLE);
        mMonthly = findViewById(R.id.tvCCRMonth);
        mMonthly.setVisibility(View.INVISIBLE);
        mThreeMonthly = findViewById(R.id.tvCCR3Month);
        mThreeMonthly.setVisibility(View.INVISIBLE);
        mYearly = findViewById(R.id.tvCCRYear);
        mYearly.setVisibility(View.INVISIBLE);
        btCalculate = findViewById(R.id.btCCR);

        btCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GenerateCCR();

            }
        });



        navigationView = findViewById(R.id.nav_View);
        drawerLayout = findViewById(R.id.ccrcLayout);

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
                        Toast.makeText(CCRepayCalc.this, "Profile page", Toast.LENGTH_SHORT);
                        Intent activityChangeIntent = new Intent(CCRepayCalc.this, ProfileManagement.class);
                        CCRepayCalc.this.startActivity(activityChangeIntent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mHome:
                        Toast.makeText(CCRepayCalc.this, "Home", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentHome = new Intent(CCRepayCalc.this, HomePage.class);
                        CCRepayCalc.this.startActivity(activityChangeIntentHome);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule1:
                        Toast.makeText(CCRepayCalc.this, "Module1", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentCalculator = new Intent(CCRepayCalc.this, FinCalc.class);
                        CCRepayCalc.this.startActivity(activityChangeIntentCalculator);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule2:
                        Toast.makeText(CCRepayCalc.this, "Module2", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentSmartInvesting = new Intent(CCRepayCalc.this, SmartInvesting.class);
                        CCRepayCalc.this.startActivity(activityChangeIntentSmartInvesting);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule3:
                        Toast.makeText(CCRepayCalc.this, "Module3", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        Intent activityChangeIntentFG = new Intent(CCRepayCalc.this, FinancialGoalSetting.class);
                        CCRepayCalc.this.startActivity(activityChangeIntentFG);
                        break;
                    case R.id.mModule4:
                        Toast.makeText(CCRepayCalc.this, "Module 4", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentQS = new Intent(CCRepayCalc.this, QuizTopicSelection.class);
                        CCRepayCalc.this.startActivity(activityChangeIntentQS);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule5:
                        Toast.makeText(CCRepayCalc.this, "Module 5", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentB = new Intent(CCRepayCalc.this, BadgesPage.class);
                        CCRepayCalc.this.startActivity(activityChangeIntentB);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule6:
                        Toast.makeText(CCRepayCalc.this, "Module 6", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentN = new Intent(CCRepayCalc.this, NoteListActivity.class);
                        CCRepayCalc.this.startActivity(activityChangeIntentN);
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
                        Toast.makeText(CCRepayCalc.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                        Intent activityChangeIntent2 = new Intent(CCRepayCalc.this, MainActivity.class);
                        CCRepayCalc.this.startActivity(activityChangeIntent2);
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
    public void GenerateCCR(){
       String outstandingBalance = mOutstandingBalance.getText().toString();
       String interestRate = mInterestRate.getText().toString();
       String fees = mFees.getText().toString();
       DecimalFormat df = new DecimalFormat("#.##");
        if (outstandingBalance.equals("")|| interestRate.equals("") || fees.equals("")) {
            Toast.makeText(CCRepayCalc.this, "Enter a Value for all fields", Toast.LENGTH_LONG).show();

        }else {
            double monthlyInterestrate = (1+((Double.parseDouble(interestRate)/100)/12));
            double oneMonthInterest  = Double.parseDouble(outstandingBalance)*monthlyInterestrate-Double.parseDouble(outstandingBalance);
            double ThreeMonthInterest = Double.parseDouble(outstandingBalance)*Math.pow(monthlyInterestrate,3)-Double.parseDouble(outstandingBalance);
            double tweleveMonthInteret = Double.parseDouble(outstandingBalance)*Math.pow(monthlyInterestrate,12)-Double.parseDouble(outstandingBalance);
            double MinMonthlyRepayment = oneMonthInterest+(Double.parseDouble(fees)/12);
            double monthPayoff = Double.parseDouble(outstandingBalance)+ oneMonthInterest +(Double.parseDouble(fees)/12) ;
            double threeMonthPayoff = Double.parseDouble(outstandingBalance) + ThreeMonthInterest+ (3*(Double.parseDouble(fees)/12));
            double tweleveMonthPayoff = Double.parseDouble(outstandingBalance) + tweleveMonthInteret+ ((Double.parseDouble(fees)));

            mSolutionTitle.setText("Possible Credit Card Payment Options include");
            mMinRepayment.setText("The Interest Only plus Fees payment is $"+ df.format(MinMonthlyRepayment));
            mMonthly.setText("Pay off the card this Month for $"+df.format(monthPayoff));
            mThreeMonthly.setText("pay off the card within three months for $"+df.format(threeMonthPayoff) +
                    " or three payments of $ " + df.format(threeMonthPayoff/3));
            mYearly.setText("pay off the card within one Year for $" + df.format(tweleveMonthPayoff)+
                    " or twelve payments of $"+df.format(tweleveMonthPayoff/12));
            mSolutionTitle.setVisibility(View.VISIBLE);
            mMonthly.setVisibility(View.VISIBLE);
            mMinRepayment.setVisibility(View.VISIBLE);
            mThreeMonthly.setVisibility(View.VISIBLE);
            mYearly.setVisibility(View.VISIBLE);


        }


    }

}
