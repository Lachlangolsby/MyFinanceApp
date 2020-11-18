package au.edu.unsw.infs3634.gamifiedlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btnEnter);
        Button button1  = findViewById(R.id.btnEnter2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {MainActivity.this.launchFinCalcActivity("Second page");
            }
        });

    }

    private void launchFinCalcActivity(String message) {
        Intent intent = new Intent(MainActivity.this, FinCalc.class);
                startActivity(intent);
    }

}