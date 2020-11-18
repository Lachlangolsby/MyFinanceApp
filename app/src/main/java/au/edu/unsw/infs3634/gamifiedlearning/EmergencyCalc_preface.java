package au.edu.unsw.infs3634.gamifiedlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EmergencyCalc_preface extends AppCompatActivity {

    private TextView mTvEmergencyTitle;
    private TextView mTvEmergencyCalcPreface;
    private Button mBtnEmergency;
    private ImageView mIvEmergencyPreface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergencycalc_preface);

        mTvEmergencyTitle = findViewById(R.id.tvEmergencyTitle);
        mTvEmergencyCalcPreface = findViewById(R.id.tvEmergencyCalcPreface);
        mBtnEmergency = findViewById(R.id.btnEmergency);
        mIvEmergencyPreface = findViewById(R.id.ivEmergencyPreface);

        mBtnEmergency.setOnClickListener
                (new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EmergencyCalc("Emergency Fund"); }
                });
    }
    private void EmergencyCalc(String message) {
        Intent intent = new Intent(EmergencyCalc_preface.this, EmergencyCalc.class);
        startActivity(intent);
    }
}
