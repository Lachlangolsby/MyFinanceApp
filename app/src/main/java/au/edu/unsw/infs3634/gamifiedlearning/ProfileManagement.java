package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firestore.v1.Value;

import java.util.Objects;



public class ProfileManagement extends AppCompatActivity {

    EditText mEmail, mPassword, mName, mPhone;
    Button mUpdateEmail, mUpdatePassword, mUpdatephone, mUpdateName;

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

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
//        mPassword = (findViewById(R.id.etPassword));
//        mUpdateEmail = findViewById(R.id.btnUpdateEmail);
//        mUpdatePassword = findViewById(R.id.btnUpdatePwd);
        mUpdateName = findViewById(R.id.btnUpdateName);
        mUpdatephone = findViewById(R.id.btnUpdatePhone);
        mName = (findViewById(R.id.etName));
        mPhone = (findViewById(R.id.etPhone));
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile !=null){
                    String fullName = userProfile.Name;
                    String email = userProfile.Email;
                    String phoneNumber = userProfile.PhoneNumber;

                    mName.setText(fullName);
                    mEmail.setText(email);
                    mPhone.setText (phoneNumber);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileManagement.this, "Data could not be loaded", Toast.LENGTH_SHORT).show();

            }
        });

        mUpdateName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                final String userid =user.getUid();
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
                    @SuppressLint("ShowToast")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User userProfile = snapshot.getValue(User.class);
                        String result = userProfile.Name;
                        if (!result.equals(mName)){
                            Toast.makeText(ProfileManagement.this, "Name Updated", Toast.LENGTH_SHORT).show();
                            reference.child(userid).child("Name").setValue(mName.getText().toString());
                        }else{
                            Toast.makeText(ProfileManagement.this, "Please enter an updated name", Toast.LENGTH_SHORT).show();

                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
            });




//        mUpdateEmail.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
//                final String userid =user.getUid();
//                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
//                reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @SuppressLint("ShowToast")
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        User userProfile = snapshot.getValue(User.class);
//                        String result = userProfile.Email;
//                        if (!result.equals(String.valueOf(mEmail))){
//                            Toast.makeText(ProfileManagement.this, "Email Updated", Toast.LENGTH_SHORT).show();
//                            reference.child(userid).child("Email").setValue(mEmail.getText().toString());
//                        }else{
//                            Toast.makeText(ProfileManagement.this, "Please enter an updated name", Toast.LENGTH_SHORT).show();
//
//                        }
//
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//            }
//        });

        mUpdatephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                final String userid =user.getUid();
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
                    @SuppressLint("ShowToast")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User userProfile = snapshot.getValue(User.class);
                        String result = userProfile.PhoneNumber;
                        if (!result.equals(String.valueOf(mPhone))){
                            Toast.makeText(ProfileManagement.this, "Phone Updated", Toast.LENGTH_SHORT).show();
                            reference.child(userid).child("PhoneNumber").setValue(mPhone.getText().toString());
                        }else{
                            Toast.makeText(ProfileManagement.this, "Please enter an updated name", Toast.LENGTH_SHORT).show();

                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

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
                    case R.id.mHome:
                        Toast.makeText(ProfileManagement.this, "Home", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentHome = new Intent(ProfileManagement.this, HomePage.class);
                        ProfileManagement.this.startActivity(activityChangeIntentHome);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule1:
                        Toast.makeText(ProfileManagement.this, "Module1", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentCalculator = new Intent(ProfileManagement.this, SmartInvesting.class);
                        ProfileManagement.this.startActivity(activityChangeIntentCalculator);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule2:
                        Toast.makeText(ProfileManagement.this, "Module2", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentSmartInvesting = new Intent(ProfileManagement.this, SmartInvesting.class);
                        ProfileManagement.this.startActivity(activityChangeIntentSmartInvesting);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule3:
                        Toast.makeText(ProfileManagement.this, "Module3", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        Intent activityChangeIntentFG = new Intent(ProfileManagement.this, FinancialGoalSetting.class);
                        ProfileManagement.this.startActivity(activityChangeIntentFG);
                        break;
                    case R.id.mModule4:
                        Toast.makeText(ProfileManagement.this, "Module 4", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentQS = new Intent(ProfileManagement.this, QuizTopicSelection.class);
                        ProfileManagement.this.startActivity(activityChangeIntentQS);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule5:
                        Toast.makeText(ProfileManagement.this, "Module 5", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentB = new Intent(ProfileManagement.this, QuizTopicSelection.class);
                        ProfileManagement.this.startActivity(activityChangeIntentB);
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
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}


