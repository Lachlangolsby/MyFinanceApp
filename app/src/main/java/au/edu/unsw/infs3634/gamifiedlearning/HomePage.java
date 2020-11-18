package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import static au.edu.unsw.infs3634.gamifiedlearning.MainActivity.mGoogleSignInClient;

public class HomePage extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    GridLayout mainGrid;
    CardView cv1, cv2,cv3,cv4,cv5,cv6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        cv1 = findViewById(R.id.cv1);
        cv2 = findViewById(R.id.cv2);
        cv3 = findViewById(R.id.cv3);
        cv4 = findViewById(R.id.cv4);
        cv5 = findViewById(R.id.cv5);
        cv6 = findViewById(R.id.cv6);




        //Set Event
        setSingleEvent(mainGrid);
        //setToggleEvent(mainGrid);
    }

    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(HomePage.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(HomePage.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(final GridLayout mainGrid) {
        //Loop all child item of Main Grid

            cv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        Intent intent = new Intent(HomePage.this, FinCalc.class);
                        intent.putExtra("info", "This is activity from card item index  ");
                        startActivity(intent);

                }
            });
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomePage.this, SmartInvesting.class);
                intent.putExtra("info", "This is activity from card item index  ");
                startActivity(intent);

            }
        });
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomePage.this, FinancialGoalSetting.class);
                intent.putExtra("info", "This is activity from card item index  ");
                startActivity(intent);

            }
        });
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomePage.this, SmartInvesting.class);
                intent.putExtra("info", "This is activity from card item index  ");
                startActivity(intent);

            }
        });
        cv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomePage.this, BadgesPage.class);
                intent.putExtra("info", "This is activity from card item index  ");
                startActivity(intent);

            }
        });
        cv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomePage.this, QuizTopicSelection.class);
                intent.putExtra("info", "This is activity from card item index  ");
                startActivity(intent);

            }
        });


        navigationView = findViewById(R.id.nav_View);
                drawerLayout = findViewById(R.id.drawerlayout);

                toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
        case R.id.mProfile:
        Toast.makeText(HomePage.this, "Profile page", Toast.LENGTH_SHORT);
            Intent activityChangeIntent = new Intent(HomePage.this, ProfileManagement.class);
            HomePage.this.startActivity(activityChangeIntent);
        drawerLayout.closeDrawers();
        break;
        case R.id.mHome:
        Toast.makeText(HomePage.this, "Home", Toast.LENGTH_SHORT);
        drawerLayout.closeDrawers();
        break;
            case R.id.mModule1:
                Toast.makeText(HomePage.this, "Module1", Toast.LENGTH_SHORT);
                Intent activityChangeIntentCalculator = new Intent(HomePage.this, FinCalc.class);
                HomePage.this.startActivity(activityChangeIntentCalculator);
                drawerLayout.closeDrawers();
                break;
        case R.id.mModule2:
        Toast.makeText(HomePage.this, "Module2", Toast.LENGTH_SHORT);
            Intent activityChangeIntentSmartInvesting = new Intent(HomePage.this, SmartInvesting.class);
            HomePage.this.startActivity(activityChangeIntentSmartInvesting);
        drawerLayout.closeDrawers();
        break;
        case R.id.mModule3:
        Toast.makeText(HomePage.this, "Module3", Toast.LENGTH_SHORT);
        drawerLayout.closeDrawers();
            Intent activityChangeIntentFG = new Intent(HomePage.this, FinancialGoalSetting.class);
            HomePage.this.startActivity(activityChangeIntentFG);
        break;
        case R.id.mModule4:
        Toast.makeText(HomePage.this, "Module 4", Toast.LENGTH_SHORT);
            Intent activityChangeIntentQS = new Intent(HomePage.this, QuizTopicSelection.class);
            HomePage.this.startActivity(activityChangeIntentQS);
        drawerLayout.closeDrawers();
        break;
            case R.id.mModule5:
                Toast.makeText(HomePage.this, "Module 5", Toast.LENGTH_SHORT);
                Intent activityChangeIntentB = new Intent(HomePage.this, BadgesPage.class);
                HomePage.this.startActivity(activityChangeIntentB);
                drawerLayout.closeDrawers();
                break;
        case R.id.mLogout:
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(HomePage.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
        Intent activityChangeIntent2 = new Intent(HomePage.this, MainActivity.class);
        HomePage.this.startActivity(activityChangeIntent2);
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
