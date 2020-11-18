package au.edu.unsw.infs3634.gamifiedlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IrregularCalc_preface extends AppCompatActivity {

    private TextView mTvRepayTitle;
    private TextView mTvCCRepayPreface;
    private Button mBtnCCRepay;
    private ImageView mIvCCRepay2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ccrepaycalc_preface);

        mTvRepayTitle = findViewById(R.id.tvRepayTitle);
        mTvCCRepayPreface = findViewById(R.id.tvCCRepayPreface);
        mBtnCCRepay = findViewById(R.id.btnCCRepay);
        mIvCCRepay2 = findViewById(R.id.ivCCRepay2);

        mBtnCCRepay.setOnClickListener
                (new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { CCRepayCalc_preface.this.CCRepayCalc_preface("CC Repay"); }
                });
    }
    private void CCRepayCalc_preface(String message) {
        Intent intent = new Intent(CCRepayCalc_preface.this, IrregularCalc_preface.class);
        startActivity(intent);
    }


}
