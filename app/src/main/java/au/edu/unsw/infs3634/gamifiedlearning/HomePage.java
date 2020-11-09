package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import static au.edu.unsw.infs3634.gamifiedlearning.MainActivity.mGoogleSignInClient;

public class HomePage extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        navigationView = findViewById(R.id.nav_View);
        drawerLayout = findViewById(R.id.drawerlayout);

        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mProfile:
                        Toast.makeText(HomePage.this, "Profile page", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mSettings:
                        Toast.makeText(HomePage.this, "Settings", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mHome:
                        Toast.makeText(HomePage.this, "Home", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule1:
                        Toast.makeText(HomePage.this, "Module1", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule2:
                        Toast.makeText(HomePage.this, "Module2", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule3:
                        Toast.makeText(HomePage.this, "Module3", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mLogout:
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(HomePage.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                        Intent activityChangeIntent = new Intent(HomePage.this, MainActivity.class);
                        HomePage.this.startActivity(activityChangeIntent);
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