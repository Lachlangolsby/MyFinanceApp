package au.edu.unsw.infs3634.gamifiedlearning;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
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
        setContentView(R.layout.activity_sign_up);

        mFullName = findViewById((R.id.ptFullName));
        mPassword = findViewById((R.id.ptPassword));
        mEmail = findViewById((R.id.ptEmail));
        mPhoneNumber = findViewById((R.id.ptPhoneNumber));
        Register = findViewById((R.id.btLogin));
        Signin = findViewById((R.id.tvAlreadyRegistered));


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progress_bar);

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), HomePage.class));
            finish();
        }

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String FullName = mFullName.getText().toString().trim();
                final String phoneNumber = mPhoneNumber.getText().toString().trim();

                if (fAuth.getCurrentUser() != null) {
                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                    finish();
                }

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required.");
                    return;
                }
                if (password.length() < 5) {
                    mPassword.setError("Password must be longer than 5 Characters");
                    return;
                }

                if (TextUtils.isEmpty(FullName)) {
                    mFullName.setError("Name is Required.");
                    return;
                }
                if (TextUtils.isEmpty(phoneNumber)) {
                    mPhoneNumber.setError("Phone Number is Required.");
                    return;
                }


                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(SignUp.this, "User Created. Sign In Now", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("fName", FullName);
                            user.put("email", email);
                            user.put("phone", phoneNumber);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "user Profile is created for: " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "Failure: " + e.toString());
                                }
                            });
                            Intent activityChangeIntent = new Intent(SignUp.this, MainActivity.class);
                           SignUp.this.startActivity(activityChangeIntent);

                        } else {
                            Toast.makeText(SignUp.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}