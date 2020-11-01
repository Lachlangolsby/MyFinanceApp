package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import static au.edu.unsw.infs3634.gamifiedlearning.MainActivity.mGoogleSignInClient;

public class HomePage extends AppCompatActivity {
    private ImageView signOutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        signOutButton = findViewById(R.id.signOut);


        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(HomePage.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                Intent activityChangeIntent = new Intent(HomePage.this, MainActivity.class);
                HomePage.this.startActivity(activityChangeIntent);

            }
        });
    }
}