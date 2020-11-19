package au.edu.unsw.infs3634.gamifiedlearning.SmartInvesting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
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

import au.edu.unsw.infs3634.gamifiedlearning.BadgesPage;
import au.edu.unsw.infs3634.gamifiedlearning.FinCalc;
import au.edu.unsw.infs3634.gamifiedlearning.SmartFinancialGoalSetting.FinancialGoalSetting;
import au.edu.unsw.infs3634.gamifiedlearning.HomePage;
import au.edu.unsw.infs3634.gamifiedlearning.SignUp.MainActivity;
import au.edu.unsw.infs3634.gamifiedlearning.Notes.NoteListActivity;
import au.edu.unsw.infs3634.gamifiedlearning.ProfileManagement;
import au.edu.unsw.infs3634.gamifiedlearning.QuizTopicSelection;
import au.edu.unsw.infs3634.gamifiedlearning.R;


public class SmartInvesting extends AppCompatActivity {
    ImageView yTLogo;
    TextView content, content2;
    Button goToQuiz;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_investing);
        yTLogo = findViewById(R.id.ivYT);
        content = findViewById(R.id.tvDescription);
        content2 = findViewById(R.id.tvContents2);
        goToQuiz = findViewById(R.id.btQuiz1);

        content.setText("Golden rules of investing\n\n" +
                "Pay off your debts first — pay off any loans, like a credit card or personal loan, before you invest.\n\n" +
                "Have emergency savings — aim to have enough set aside to cover three months' expenses, so you won't have to sell an investment if you need cash quickly.\n\n" +
                "Develop an investing plan — define your financial goals, risk tolerance and investment time frame.\n\n" +
                "Research different asset classes — understand the risks and returns, and how they can help you reach your financial goals.\n\n" +
                "Diversify your investments — spread your money across and within asset classes to lower your portfolio's risk.\n\n" +
                "Keep track of your investments — review them regularly and make sure you're on track.\n\n"+
                "Developing an investing plan \n\n" +
                "1. Review your finances\n\n" +
                        "Before you invest, review your financial situation.\n" +
                        "Write down what you owe (your debts) and what you own (your assets). For your assets include your:\n" +
                        "\n" +
                        "- super\n" +
                        "- home\n" +
                        "- savings\n" +
                        "- other investments\n\n" +
                        "Then write down your income and expenses. Budget planning can help you track what money is coming in and going out. This will help you see how much you can put toward investing regularly.\n" +
                        "\n" +
                        "2. Set your financial goals\n\n" +
                        "Write down your financial goals. For each goal include how much you'll need and how long you have to reach it. For example, taking a $10,000 holiday in one year, or reaching $500,000 in superannuation before you retire.\n" +
                        "\n" +
                        "Then divide your goals into:\n" +
                        "- short term (0 to 2 years)\n" +
                        "- medium term (3 to 5 years)\n" +
                        "- long term (5 years or more)\n\n" +
                        "Setting and defining your financial goals will help you pick the right investment to reach each goal.\n" +
                        "\n" +
                        "3. Understand investment risks\n\n " +
                        "Investment risk is the likelihood that you'll lose some or all the money you've invested. This can be due to your investment falling in value or not performing how you expected. All assets carry investment risks — some are riskier than others. \n" +
                        "\n" +
                        "Risks that can affect the value of your investment include:\n"+
                "- Interest rate risk\n"+
                        "- Interest rate risk\n"+
                        "- Market risk\n"+
                        "- Currency Risk\n"+
                        "- Liquidity risk\n"+
                        "- Credit risk\n"+
                        "- Concentration risk\n"+
                        "- Inflation risk\n"+
                        "- Gearing risk\n\n"+
                "Risk and return\n" +
                        "As a general rule, the higher the expected return on an investment, the higher the risk of the investment. The lower the expected return, the lower the risk. Lower risk means the returns are more stable and there is a lower chance you could lose money.\n" +
                        "\n" +
                        "For example, a government bond is a low risk investment. It pays interest, and the value of the investment doesn't change too much in the short term. Shares are a higher risk investment. The price of a share can move up and down a lot over a short amount of time.\n" +
                        "\n" +
                        "The graph below shows the risk and return relationship for different asset classes."
                );
        content2.setText("Know your risk tolerance\n\n" +
                "Your risk tolerance depends on your ability to cope with falls in the value of your investment. Your age, capacity to recover from financial loss, financial goals and your health are some of the factors that may influence your risk tolerance. \n" +
                "\n" +
                "Ask yourself: how would I feel if I woke up tomorrow and found the value of my investments had dropped 20%?\n" +
                "\n" +
                "If this drop would cause you to worry and withdraw your money, high risk investments are not for you.\n" +
                "\n" +
                "Each investor's risk tolerance is different and for different financial goals that have different investment time frames you may be willing to accept different levels of risk.\n" +
                "\n" +
                "It's important to understand your risk tolerance and find investments that are aligned to it.\n" +
                "\n" +
                "4. Research your investment options\n\n" +
                "To find the right investments, you need to think about:\n" +
                "\n" +
                "- Return: what is the expected return on the investment? Does it come from income or capital growth?\n" +
                "- Time frame: how long do you need to invest to get the expected return?\n" +
                "- Risk: what types of risk does the investment involve? Are you comfortable to take on these risks?\n" +
                "- Access to cash (liquidity): how long will it take to sell the investment and get your cash out?\n" +
                "- Cost to buy and sell: how much will it cost to buy and sell the investment?\n" +
                "- Tax: how much tax will you pay on earnings (income and capital gains) from the investment?\n\n" +
                "5. Build your portfolio\n\n" +
                "The way you structure your portfolio, will depend on your financial goals, investing time frame and risk tolerance.\n" +
                "\n" +
                "For short-term goals, lower-risk investment options are better. Consider investments like a savings account, term deposit or government bonds. These investments are lower risk as they're less likely to fall in value and you can access your money.\n" +
                "\n" +
                "For longer-term goals, investments with higher returns such as shares and property, can be better. These investments are higher risk but you're investing long term, so you can ride out any short-term falls in value.\n" +
                "\n" +
                "It's important to make sure you diversify your portfolio across different asset classes and within each asset class. This protects you against losing too much if the value of one investment falls. See diversification for how this strategy can help you.\n" +
                "\n" +
                "If you need help with investing\n" +
                "A financial adviser can help you work out your risk tolerance, set goals and choose the right investments.\n" +
                "\n" +
                "6. Monitor your investments\n\n" +
                "It's important to review your investments regularly to make sure they're performing as expected. And check whether you're on track to reach your financial goals." +
                "Source:(https://moneysmart.gov.au/how-to-invest)\n\n\n"

        );

        yTLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=yRr0_gJ-3mI"));
                startActivity(intent);
            }
        });
        goToQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityChangeIntent = new Intent(SmartInvesting.this, SmartInvestingQuizLanding.class);
                SmartInvesting.this.startActivity(activityChangeIntent);

            }
        });


    navigationView = findViewById(R.id.nav_View);
    drawerLayout = findViewById(R.id.smartinvestinglayout);

    toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mProfile:
                        Toast.makeText(SmartInvesting.this, "Profile page", Toast.LENGTH_SHORT);
                        Intent activityChangeIntent = new Intent(SmartInvesting.this, ProfileManagement.class);
                        SmartInvesting.this.startActivity(activityChangeIntent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mHome:
                        Toast.makeText(SmartInvesting.this, "Home", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentHome = new Intent(SmartInvesting.this, HomePage.class);
                        SmartInvesting.this.startActivity(activityChangeIntentHome);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule1:
                        Toast.makeText(SmartInvesting.this, "Module1", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentCalculator = new Intent(SmartInvesting.this, FinCalc.class);
                        SmartInvesting.this.startActivity(activityChangeIntentCalculator);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule2:
                        Toast.makeText(SmartInvesting.this, "Module2", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentSmartInvesting = new Intent(SmartInvesting.this, SmartInvesting.class);
                        SmartInvesting.this.startActivity(activityChangeIntentSmartInvesting);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule3:
                        Toast.makeText(SmartInvesting.this, "Module3", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        Intent activityChangeIntentFG = new Intent(SmartInvesting.this, FinancialGoalSetting.class);
                        SmartInvesting.this.startActivity(activityChangeIntentFG);
                        break;
                    case R.id.mModule4:
                        Toast.makeText(SmartInvesting.this, "Module 4", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentQS = new Intent(SmartInvesting.this, QuizTopicSelection.class);
                        SmartInvesting.this.startActivity(activityChangeIntentQS);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule5:
                        Toast.makeText(SmartInvesting.this, "Module 5", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentB = new Intent(SmartInvesting.this, BadgesPage.class);
                        SmartInvesting.this.startActivity(activityChangeIntentB);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule6:
                        Toast.makeText(SmartInvesting.this, "Module 6", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentN = new Intent(SmartInvesting.this, NoteListActivity.class);
                        SmartInvesting.this.startActivity(activityChangeIntentN);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mLogout:
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(SmartInvesting.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                        Intent activityChangeIntent2 = new Intent(SmartInvesting.this, MainActivity.class);
                        SmartInvesting.this.startActivity(activityChangeIntent2);
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


