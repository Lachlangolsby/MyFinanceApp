package au.edu.unsw.infs3634.gamifiedlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinCalc extends AppCompatActivity {
    private TextView mFinCalcTitle;
    private TextView mEmergencyFund;
    private TextView mCCRepay;
    private TextView mIrregular;
    private TextView mCompound;
    private ImageView mEmergencyFundIcon;
    private ImageView mCCRepayIcon;
    private ImageView mIrregularIcon;
    private ImageView mCompoundIcon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mFinCalcTitle = findViewById(R.id.tvPage2);
        mEmergencyFund = findViewById(R.id.tvEmergency);
        mCCRepay = findViewById(R.id.tvCCRepay);
        mIrregular = findViewById(R.id.tvIrregular);
        mCompound = findViewById(R.id.tvCompound);
        mEmergencyFundIcon = findViewById(R.id.ivEmergency);
        mCCRepayIcon = findViewById(R.id.ivCCRepay);
        mIrregularIcon = findViewById(R.id.ivIrregular);
        mCompoundIcon = findViewById(R.id.ivCompound);

        //Set the onClick Listeners so user clicks and goes into associated preface page
        mEmergencyFundIcon.setOnClickListener
                (new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { au.edu.unsw.infs3634.gamifiedlearning.FinCalc.this.gotoEmergencyFundPreface("Emergency Fund"); }
                });

        mIrregularIcon.setOnClickListener
                (new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { au.edu.unsw.infs3634.gamifiedlearning.FinCalc.this.gotoIrregularPaymentsPreface("Irregular Payments"); }
                });

        mCCRepayIcon.setOnClickListener
                (new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { au.edu.unsw.infs3634.gamifiedlearning.FinCalc.this.gotoCCRepaymentPreface("Credit Card Repayments"); }
                });

        mCompoundIcon.setOnClickListener
                (new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { au.edu.unsw.infs3634.gamifiedlearning.FinCalc.this.gotoCompoundInterestPreface("Compound Interest"); }
                });

    }
    //Preface page intents
    private void gotoEmergencyFundPreface(String message) {
        Intent intent = new Intent(au.edu.unsw.infs3634.gamifiedlearning.FinCalc.this, EmergencyCalc_preface.class);
        startActivity(intent);
    }

    private void gotoIrregularPaymentsPreface(String message) {
        Intent intent = new Intent(au.edu.unsw.infs3634.gamifiedlearning.FinCalc.this, IrregularCalc_preface.class);
        startActivity(intent);
    }

    private void gotoCCRepaymentPreface(String message) {
        Intent intent = new Intent(au.edu.unsw.infs3634.gamifiedlearning.FinCalc.this, CCRepayCalc_preface.class);
        startActivity(intent);
    }

    private void gotoCompoundInterestPreface(String message) {
        Intent intent = new Intent(au.edu.unsw.infs3634.gamifiedlearning.FinCalc.this, CompoundCalc_preface.class);
        startActivity(intent);
    }
}
