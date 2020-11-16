package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class FinancialGoalSetting extends AppCompatActivity {
    ImageView yt, calc;
    TextView content, content2, link1,link2;
    Button goToQuiz;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_goal_setting);
                content = findViewById(R.id.tvDescription);
        content2 =findViewById(R.id.tvcontent2);
        yt = findViewById(R.id.ivYT);
        calc = findViewById(R.id.ivCalculator);
        link1 = findViewById(R.id.tvLink1);
        link2 = findViewById(R.id.tvLink2);
        goToQuiz = findViewById(R.id.btQuiz);


        content.setText(
                "1. What is your income?\n\n" +
                "Identify your sources of income. This may be your full time, part time or casual job. Ask yourself the following questions:\n" +
                "\n" +
                "- How often do you get paid? For example, weekly, fortnightly, etc.\n" +
                "- What day do you get paid? For example, every second Wednesday, the 14th of every month etc.\n" +
                "- How much do you get paid?\n" +
                "- Are you on a salary, and therefore is your income the same every paycheck?\n" +
                "- Are you a casual worker, and does your income change every paycheck? If so, what is the average amount you get paid per month?\n" +
                "- What will your total income be for the month?\n" +
                "\n" +
                "2. Add up your expenses\n\n" +
                "Look over the past month and write down all the things you bought. Try and be as specific as possible. You may need to refer to your online banking app, past receipts and bills.\n" +
                "\n" +
                "3. Prioritise your spending – what are the ‘wants’ vs ‘needs’?\n\n" +
                "Once you’ve made a comprehensive list of each of your expenses it is time to determine whether they are a ‘need’ or a ‘want’.\n" +
                "\n" +
                "- ‘Needs’ are goods and services that are required to survive and function in society.\n" +
                "\n" +
                "- ‘Wants’ are goods and services that you desire to have and are not essential.\n" +
                "\n" +
                "Clearly identify what category each of your expenses falls within by:\n" +
                "\n" +
                "Highlighting all the expenses that are ‘needs’.\n" +
                "Placing an asterisk next to all the expenses that are ‘wants’.\n" +
                "\n" +
                "By identifying your ‘wants’ and ‘needs’, you’ll be able to identify areas where you can reduce your spending. If you are saving up for something or finances are tight, you may want to reduce your ‘wants’ where necessary. In times of greater financial security or during special times of the year (birthdays, celebrations, etc), you may choose to be more lenient.\n" +
                "\n" +

                "4. Take a little time to find ways to save\n\n" +
                "There are many ways you can save money without having to drastically change your behaviours. Here are a few useful tips to get you started:\n" +
                "\n" +
                "Save money when socialising with friends by doing things that are free. Why not go for a walk or head down to the beach?\n\n" +
                "Try and do your grocery shopping with a group (eg your roommates). This means you’ll be able to buy more items in bulk.\n\n" +
                "Make the most of rewards programs every time you shop. For example, Woolworths ‘everyday rewards’ offer $10 off your shopping when you earn a certain amount of points, and Flybys can be used for many different shopping outlets including Kmart, Coles and Optus.\n\n " +
                "5. Set your saving goals and create your budget\n\n" +
                "Set one to three financial goals\n" +
                "For example, ‘have $5,000 in my savings account’ or ‘repay my credit card debt in six months’\n\n" +
                "Use the Money Smart Goals Calculator to recieve a personalised savings plan." );

        content2.setText("People who set a savings goal and commit to a plan are more likely to achieve it.\n\n" +
                "Allocate a set amount to an emergency fund. Decide on an amount you would like to allocate to an ‘emergency fund.’ This is a savings account that should not be touched, and acts as a provision for unexpected circumstances, such as Covid-19. This fund can be used if something unexpected happens, such as losing your job.\n\n" +
                "You might want to aim to have enough in your emergency fund to cover at least two to three months’ worth of expenses. Saving just $10 a week adds up to over $500 in a year.\n\n" +
                "Decide how much spending money you will allow yourself\n" +
                "This money is for your ‘wants’ such as entertainment, eating out and hobbies. Setting yourself a specific amount will help you to keep within your limit.\n" +
                "Future fund\n" +
                "Lastly, set aside any leftover money for a future fund to save for things in the longer term, such as buying a house or car.\n" +
                "Once you have determined all your costs and allocated an amount to each fund, record your spending and income in a budget so that you have a clear record of where your money is coming from and going to.\n" +
                "\n" +
                "This template allows you to input your monthly income and expenses. Then, you can choose how to allocate the surplus amount between your emergency fund, spending money and future fund. Alternatively, use the Money Smart Budgeting planner.\n" +
                "\n" +
                "\n" +
                "6. Commit to a routine\n\n" +
                "After creating a budget, you need to commit to a consistent routine. This can mean scheduling just 30 minutes at the start of every month to create your budget and updating it every few days with new income or expenses to ensure you stay on track.\n" +
                "\n" +
                "7. Lastly, if you can’t stick to your budget, or if you find yourself in distress, seek help\n\n" +
                "The Covid-19 pandemic is a challenging time for everyone, so you’re not alone! There are a number of ways to seek help if you have found yourself to be in a situation of financial distress. Contact a financial counselling service for free, or explore the following resources that provide financial advice and information:");


        link1.setText("- Asics Money Smart Website");
        link2.setText("- The National Debt Helpline");









                yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Q24OCPVTvvI"));
                startActivity(intent);
            }
        });

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://moneysmart.gov.au/saving/savings-goals-calculator"));
                startActivity(intent);
            }
        });

        link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://moneysmart.gov.au/"));
                startActivity(intent);
            }
        });
        link2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ndh.org.au/"));
                startActivity(intent);
            }
        });

        goToQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityChangeIntent = new Intent(FinancialGoalSetting.this, FinancialGoalSettingQuizLanding.class);
                FinancialGoalSetting.this.startActivity(activityChangeIntent);

            }
        });


    navigationView = findViewById(R.id.nav_View);
    drawerLayout = findViewById(R.id.financialGoalSettinglayout);

    toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mProfile:
                        Toast.makeText(FinancialGoalSetting.this, "Profile page", Toast.LENGTH_SHORT);
                        Intent activityChangeIntent = new Intent(FinancialGoalSetting.this, ProfileManagement.class);
                        FinancialGoalSetting.this.startActivity(activityChangeIntent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mHome:
                        Toast.makeText(FinancialGoalSetting.this, "Home", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentHome = new Intent(FinancialGoalSetting.this, HomePage.class);
                        FinancialGoalSetting.this.startActivity(activityChangeIntentHome);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule1:
                        Toast.makeText(FinancialGoalSetting.this, "Module1", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentCalculator = new Intent(FinancialGoalSetting.this, SmartInvesting.class);
                        FinancialGoalSetting.this.startActivity(activityChangeIntentCalculator);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule2:
                        Toast.makeText(FinancialGoalSetting.this, "Module2", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentSmartInvesting = new Intent(FinancialGoalSetting.this, SmartInvesting.class);
                        FinancialGoalSetting.this.startActivity(activityChangeIntentSmartInvesting);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule3:
                        Toast.makeText(FinancialGoalSetting.this, "Module3", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        Intent activityChangeIntentFG = new Intent(FinancialGoalSetting.this, FinancialGoalSetting.class);
                        FinancialGoalSetting.this.startActivity(activityChangeIntentFG);
                        break;
                    case R.id.mModule4:
                        Toast.makeText(FinancialGoalSetting.this, "Module 4", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentQS = new Intent(FinancialGoalSetting.this, QuizTopicSelection.class);
                        FinancialGoalSetting.this.startActivity(activityChangeIntentQS);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule5:
                        Toast.makeText(FinancialGoalSetting.this, "Module 5", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentB = new Intent(FinancialGoalSetting.this, QuizTopicSelection.class);
                        FinancialGoalSetting.this.startActivity(activityChangeIntentB);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mLogout:
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(FinancialGoalSetting.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                        Intent activityChangeIntent2 = new Intent(FinancialGoalSetting.this, MainActivity.class);
                        FinancialGoalSetting.this.startActivity(activityChangeIntent2);
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




//"https://www.youtube.com/watch?v=7UpADTIi9uI
// https://www.youtube.com/watch?v=Q24OCPVTvvI