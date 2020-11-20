package au.edu.unsw.infs3634.gamifiedlearning;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
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

import au.edu.unsw.infs3634.gamifiedlearning.Notes.NoteListActivity;
import au.edu.unsw.infs3634.gamifiedlearning.SignUp.MainActivity;
import au.edu.unsw.infs3634.gamifiedlearning.SignUp.User;
import au.edu.unsw.infs3634.gamifiedlearning.SmartFinancialGoalSetting.FinancialGoalSetting;
import au.edu.unsw.infs3634.gamifiedlearning.SmartInvesting.SmartInvesting;

public class BadgesPage extends AppCompatActivity {
    ImageView mivSI3,mivSI5,mivFG3,mivFG5,mivMember;
    TextView mtvSI3,mtvSI5,mtvFG3,mtvFG5,mtvMember;
    private FirebaseAuth fAuth;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badges_page);
        mivFG3 = findViewById(R.id.ivFG3);
        mivFG5 = findViewById(R.id.ivFG5);
        mivSI3 = findViewById(R.id.ivSI3);
        mivSI5 = findViewById(R.id.ivSI5);
        mivMember= findViewById(R.id.ivMembers);
        mtvFG3 = findViewById(R.id.tvFG3);
        mtvFG5 = findViewById(R.id.tvFG5);
        mtvSI3 = findViewById(R.id.tvSI3);
        mtvSI5 = findViewById(R.id.tvSI5);
        mtvMember= findViewById(R.id.tvMembers);

        mivFG3.setAlpha(75);
        mivFG5.setAlpha(75);
        mivSI3.setAlpha(75);
        mivSI5.setAlpha(75);
        mivMember.setAlpha(75);
        fAuth = FirebaseAuth.getInstance();
        MembersBadge();
        FG3badge();
        FG5badge();
        SI3badge();
        SI5badge();


        navigationView = findViewById(R.id.nav_View);
        drawerLayout = findViewById(R.id.badgepagedrawer);

        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mProfile:
                        Toast.makeText(BadgesPage.this, "Profile page", Toast.LENGTH_SHORT);
                        Intent activityChangeIntent = new Intent(BadgesPage.this, ProfileManagement.class);
                        BadgesPage.this.startActivity(activityChangeIntent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mHome:
                        Intent activityChangeIntentHome = new Intent(BadgesPage.this, HomePage.class);
                        BadgesPage.this.startActivity(activityChangeIntentHome);
                        Toast.makeText(BadgesPage.this, "Home", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule1:
                        Toast.makeText(BadgesPage.this, "Module1", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentCalculator = new Intent(BadgesPage.this, FinCalc.class);
                        BadgesPage.this.startActivity(activityChangeIntentCalculator);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule2:
                        Toast.makeText(BadgesPage.this, "Module2", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentSmartInvesting = new Intent(BadgesPage.this, SmartInvesting.class);
                        BadgesPage.this.startActivity(activityChangeIntentSmartInvesting);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule3:
                        Toast.makeText(BadgesPage.this, "Module3", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        Intent activityChangeIntentFG = new Intent(BadgesPage.this, FinancialGoalSetting.class);
                        BadgesPage.this.startActivity(activityChangeIntentFG);
                        break;
                    case R.id.mModule4:
                        Toast.makeText(BadgesPage.this, "Module 4", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentQS = new Intent(BadgesPage.this, QuizTopicSelection.class);
                        BadgesPage.this.startActivity(activityChangeIntentQS);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule5:
                        Toast.makeText(BadgesPage.this, "Module 5", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentB = new Intent(BadgesPage.this, BadgesPage.class);
                        BadgesPage.this.startActivity(activityChangeIntentB);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule6:
                        Toast.makeText(BadgesPage.this, "Module 6", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentN = new Intent(BadgesPage.this, NoteListActivity.class);
                        BadgesPage.this.startActivity(activityChangeIntentN);
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
                        Toast.makeText(BadgesPage.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                        Intent activityChangeIntent2 = new Intent(BadgesPage.this, MainActivity.class);
                        BadgesPage.this.startActivity(activityChangeIntent2);
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


    public void MembersBadge(){
        if (fAuth.getCurrentUser().isEmailVerified() == true){
            mivMember.setAlpha(255);
            mtvMember.setTextColor(Color.parseColor("#000000"));


        }
    }
    public void FG3badge(){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String userid =user.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                    String result = userProfile.FGSScore;
                    if (Integer.parseInt(result)> 2.5){
                        mivFG3.setAlpha(255);
                        mtvFG3.setTextColor(Color.parseColor("#000000"));

                    }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        }
    public void FG5badge(){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String userid =user.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                String result = userProfile.FGSScore;
                if (Integer.parseInt(result) == 5){
                    mivFG5.setAlpha(255);
                    mtvFG5.setTextColor(Color.parseColor("#000000"));

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void SI3badge(){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String userid =user.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                String result = userProfile.SIScore;
                if (Integer.parseInt(result)> 2.5){
                    mivSI3.setAlpha(255);
                    mtvSI3.setTextColor(Color.parseColor("#000000"));

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void SI5badge(){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String userid =user.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                String result = userProfile.SIScore;
                if (Integer.parseInt(result) == 5){
                    mivSI5.setAlpha(255);
                    mtvSI5.setTextColor(Color.parseColor("#000000"));

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    }

