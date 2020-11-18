package au.edu.unsw.infs3634.gamifiedlearning;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinCalc extends AppCompatActivity {
    private TextView mFinCalcTitle;
    private TextView mEmergencyFund;
    private TextView mCCRepay;
    private TextView mIrregular;
    private TextView mCompound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mFinCalcTitle = findViewById(R.id.tvPage2);
    }
}
