package au.edu.unsw.infs3634.gamifiedlearning.SignUp;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import au.edu.unsw.infs3634.gamifiedlearning.R;

public class SignUp extends AppCompatActivity {
    // Pecalring variables to be used in class
    public static final String TAG = "TAG";
    String userID;
    ProgressBar progressBar;
    private EditText mFullName, mEmail, mPassword, mPhoneNumber;
    private Button Register;
    private TextView Signin;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // assigning correct xml layout
        setContentView(R.layout.activity_sign_up);


        // animating background
        ConstraintLayout background = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) background.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();


        // Assigning variables to UI elements
        mFullName = findViewById((R.id.ptFullName));
        mPassword = findViewById((R.id.ptPassword));
        mEmail = findViewById((R.id.ptEmail));
        mPhoneNumber = findViewById((R.id.ptPhoneNumber));
        Register = findViewById((R.id.btLogin));
        Signin = findViewById((R.id.tvAlreadyRegistered));
        progressBar = findViewById(R.id.progressBar33);


        // connecting to Firebase
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        // on click listner logic for registering
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String FullName = mFullName.getText().toString().trim();
                final String phoneNumber = mPhoneNumber.getText().toString().trim();

                // assessing field not empty
                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required.");
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }

                // assessing field not empty
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required.");
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }

                // assessing field meets requirements of firebase
                if (password.length() < 5) {
                    mPassword.setError("Password must be longer than 5 Characters");
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }

                // assessing field not empty
                if (TextUtils.isEmpty(FullName)) {
                    mFullName.setError("Name is Required.");
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }

                // assessing field not empty
                if (TextUtils.isEmpty(phoneNumber)) {
                    mPhoneNumber.setError("Phone Number is Required.");
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }


                // using firebase method to create user
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // storing user in realtime db and assigning them default quiz scores of 0 the redirecting to login
                            Toast.makeText(SignUp.this, "User Created. Sign In Now", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("Users").document(userID);
                            User user = new User(FullName, email, phoneNumber, "0", "0");
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUp.this, "User Created. Sign In Now", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.INVISIBLE);
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    } else {
                                        Toast.makeText(SignUp.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }
                                }
                            });
                            // redirecting to login
                            Intent activityChangeIntent = new Intent(SignUp.this, MainActivity.class);
                           SignUp.this.startActivity(activityChangeIntent);
                            progressBar.setVisibility(View.INVISIBLE);

                        } else {
                            Toast.makeText(SignUp.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });

        // if user already has account redirect to sign in
        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}