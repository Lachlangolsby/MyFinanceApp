package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firestore.v1.Value;

import java.util.Objects;



public class ProfileManagement extends AppCompatActivity {

    EditText mEmail, mPassword, mName, mPhone;
    Button mUpdateEmail, mUpdatePassword, mUpdatephone, mUpdateName;

    FirebaseUser user;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);
        user = FirebaseAuth.getInstance().getCurrentUser();


        mEmail = (findViewById(R.id.etEmail));
        mPassword = (findViewById(R.id.etPassword));
        mUpdateEmail = findViewById(R.id.btnUpdateEmail);
        mUpdatePassword = findViewById(R.id.btnUpdatePwd);
        mUpdateName = findViewById(R.id.btnUpdateName);
        mUpdatephone = findViewById(R.id.btnUpdatePhone);
        mName = (findViewById(R.id.etName));
        mPhone = (findViewById(R.id.etPhone));


        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();

                // UID specific to the provider
                String uid = profile.getUid();

                // Name, email address, and profile photo Url
                String name = profile.getDisplayName();
                String email = profile.getEmail();
                String phone = profile.getPhoneNumber();
                Uri photoUrl = profile.getPhotoUrl();

                mEmail.setText(email);
                mName.setText(name);
                mPhone.setText(phone);
                mPassword.setText("*******");
            }
        }

        mUpdateName.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                UserProfileChangeRequest.Builder builder = new UserProfileChangeRequest.Builder();
                builder.setDisplayName(mName.toString().trim());
                UserProfileChangeRequest nameUpdate = builder.build();
                FirebaseAuth.getInstance().getCurrentUser().updateProfile(nameUpdate);
                Toast.makeText(getApplicationContext(), "Name Details updated", Toast.LENGTH_SHORT).show();


            }
        });

        mUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserProfileChangeRequest.Builder builder = new UserProfileChangeRequest.Builder();
                builder.setDisplayName(mEmail.toString().trim());
                UserProfileChangeRequest emailUpdate = builder.build();
                FirebaseAuth.getInstance().getCurrentUser().updateEmail(String.valueOf(emailUpdate));
                Toast.makeText(getApplicationContext(), "Email Updated", Toast.LENGTH_SHORT).show();

            }
        });

        mUpdatephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserProfileChangeRequest.Builder builder = new UserProfileChangeRequest.Builder();
                builder.setDisplayName(mPhone.toString().trim());
                UserProfileChangeRequest phoneUpdate = builder.build();
                FirebaseAuth.getInstance().getCurrentUser().updateProfile(phoneUpdate);
                Toast.makeText(getApplicationContext(), "Phone details Updated", Toast.LENGTH_SHORT).show();
            }
        });

        navigationView = findViewById(R.id.View);
        drawerLayout = findViewById(R.id.layout);

        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mProfile:
                        Toast.makeText(ProfileManagement.this, "Profile page", Toast.LENGTH_SHORT);
                        Intent activityChangeIntent = new Intent(ProfileManagement.this, ProfileManagement.class);
                        ProfileManagement.this.startActivity(activityChangeIntent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mSettings:
                        Toast.makeText(ProfileManagement.this, "Settings", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mHome:
                        Toast.makeText(ProfileManagement.this, "Home", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule1:
                        Toast.makeText(ProfileManagement.this, "Module1", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule2:
                        Toast.makeText(ProfileManagement.this, "Module2", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule3:
                        Toast.makeText(ProfileManagement.this, "Module3", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mLogout:
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(ProfileManagement.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                        Intent activityChangeIntent2 = new Intent(ProfileManagement.this, MainActivity.class);
                        ProfileManagement.this.startActivity(activityChangeIntent2);
                        drawerLayout.closeDrawers();
                        break;
                }

                return false;
            }
        });


    }
}


