package au.edu.unsw.infs3634.gamifiedlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CompoundCalc_preface extends AppCompatActivity {

    private TextView mTvCompoundTitle;
    private TextView mTvCompoundPreface;
    private Button mBtnCompound;
    private ImageView mIvCompoundPreface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ccrepaycalc_preface);

        mTvCompoundTitle = findViewById(R.id.tvRepayTitle);
        mTvCompoundPreface = findViewById(R.id.tvCCRepayPreface);
        mBtnCompound = findViewById(R.id.btnCCRepay);
        mIvCompoundPreface = findViewById(R.id.ivCCRepay2);

        mBtnCompound.setOnClickListener
                (new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { CompoundCalc_preface.this.C; }
                });
    }
    private void CCRepayCalc_preface(String message) {
        Intent intent = new Intent(CompoundCalc_preface.this, CompoundCalc.class);
        startActivity(intent);
    }
}
