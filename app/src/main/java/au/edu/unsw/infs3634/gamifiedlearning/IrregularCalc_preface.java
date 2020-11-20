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

public class IrregularCalc_preface extends AppCompatActivity {

    private TextView mTvIrregularTitle;
    private TextView mTvIrregularCalcPreface;
    private Button mBtnIrregular;
    private ImageView mIvIrregularPreface;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.irregularcalc_preface);

        mTvIrregularTitle = findViewById(R.id.tvIrregularTitle);
        mTvIrregularCalcPreface = findViewById(R.id.tvIrregularCalcPreface);
        mBtnIrregular = findViewById(R.id.btnIrregular);
        mIvIrregularPreface = findViewById(R.id.ivIrregularPreface);

        mBtnIrregular.setOnClickListener
                (new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IrregularCalc_preface("Irregular Payments"); }
                });
        navigationView = findViewById(R.id.nav_View);
        drawerLayout = findViewById(R.id.irLayout);

        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mProfile:
                        Toast.makeText(IrregularCalc_preface.this, "Profile page", Toast.LENGTH_SHORT);
                        Intent activityChangeIntent = new Intent(IrregularCalc_preface.this, ProfileManagement.class);
                        IrregularCalc_preface.this.startActivity(activityChangeIntent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mHome:
                        Toast.makeText(IrregularCalc_preface.this, "Home", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentHome = new Intent(IrregularCalc_preface.this, HomePage.class);
                        IrregularCalc_preface.this.startActivity(activityChangeIntentHome);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule1:
                        Toast.makeText(IrregularCalc_preface.this, "Module1", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentCalculator = new Intent(IrregularCalc_preface.this, FinCalc.class);
                        IrregularCalc_preface.this.startActivity(activityChangeIntentCalculator);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule2:
                        Toast.makeText(IrregularCalc_preface.this, "Module2", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentSmartInvesting = new Intent(IrregularCalc_preface.this, SmartInvesting.class);
                        IrregularCalc_preface.this.startActivity(activityChangeIntentSmartInvesting);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule3:
                        Toast.makeText(IrregularCalc_preface.this, "Module3", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        Intent activityChangeIntentFG = new Intent(IrregularCalc_preface.this, FinancialGoalSetting.class);
                        IrregularCalc_preface.this.startActivity(activityChangeIntentFG);
                        break;
                    case R.id.mModule4:
                        Toast.makeText(IrregularCalc_preface.this, "Module 4", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentQS = new Intent(IrregularCalc_preface.this, QuizTopicSelection.class);
                        IrregularCalc_preface.this.startActivity(activityChangeIntentQS);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule5:
                        Toast.makeText(IrregularCalc_preface.this, "Module 5", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentB = new Intent(IrregularCalc_preface.this, BadgesPage.class);
                        IrregularCalc_preface.this.startActivity(activityChangeIntentB);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule6:
                        Toast.makeText(IrregularCalc_preface.this, "Module 6", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentN = new Intent(IrregularCalc_preface.this, NoteListActivity.class);
                        IrregularCalc_preface.this.startActivity(activityChangeIntentN);
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
                        Toast.makeText(IrregularCalc_preface.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                        Intent activityChangeIntent2 = new Intent(IrregularCalc_preface.this, MainActivity.class);
                        IrregularCalc_preface.this.startActivity(activityChangeIntent2);
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

    private void IrregularCalc_preface(String message) {
        Intent intent = new Intent(IrregularCalc_preface.this, IrregularCalc.class);
        startActivity(intent);
    }

}
