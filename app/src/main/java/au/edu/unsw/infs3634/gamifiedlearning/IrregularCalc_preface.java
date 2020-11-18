package au.edu.unsw.infs3634.gamifiedlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IrregularCalc_preface extends AppCompatActivity {

    private TextView mTvIrregularTitle;
    private TextView mTvIrregularCalcPreface;
    private Button mBtnIrregular;
    private ImageView mIvIrregularPreface;

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
    }
    private void IrregularCalc_preface(String message) {
        Intent intent = new Intent(IrregularCalc_preface.this, IrregularCalc.class);
        startActivity(intent);
    }

}
