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
        setContentView(R.layout.compoundcalc_preface);

        mTvCompoundTitle = findViewById(R.id.tvCompoundTitle);
        mTvCompoundPreface = findViewById(R.id.tvCompoundPreface);
        mBtnCompound = findViewById(R.id.btnCompound);
        mIvCompoundPreface = findViewById(R.id.ivCompoundPreface);

        mBtnCompound.setOnClickListener
                (new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CompoundCalc("Compound Calculator"); }
                });
    }
    private void CompoundCalc(String message) {
        Intent intent = new Intent(CompoundCalc_preface.this, CompoundCalc.class);
        startActivity(intent);
    }
}
