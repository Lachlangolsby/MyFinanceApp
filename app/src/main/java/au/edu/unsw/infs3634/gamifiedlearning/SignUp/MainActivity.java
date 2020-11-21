package au.edu.unsw.infs3634.gamifiedlearning.SignUp;

import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import au.edu.unsw.infs3634.gamifiedlearning.HomePage;
import au.edu.unsw.infs3634.gamifiedlearning.R;

public class MainActivity extends AppCompatActivity {
    // Variables used for Signing in
    Button mLogin;
    TextView mCreateButton, mForgotPassword;
    private String Tag = "MainActivity ";
    private FirebaseAuth mAuth;
    private int resultCodeSI = 1;
    private EditText mEmail, mPassword;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // loading correct XML layout
        setContentView(R.layout.activity_main);


        // Animating background
        ConstraintLayout background = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) background.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();


        // assigning variable to correct xml elements
        mAuth = FirebaseAuth.getInstance();
        mEmail = findViewById((R.id.ptEmail));
        mPassword = findViewById((R.id.ptPassword));
        mLogin = findViewById(R.id.btLogin);
        mCreateButton = findViewById(R.id.tvCreateAccount);
        mForgotPassword = findViewById(R.id.tvForgotPassword);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar2);


        // login button on click listener
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                // retrieving variables from edit text
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                //Assessing field input is not null
                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("email is Required.");
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }

                //Assessing field input is not null
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required.");
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }

                //Assessing field input meets firebase requirements
                if (password.length() < 5) {
                    mPassword.setError("Password Must be at least 5 Characters");
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }


                // using firebase authentication signin method and redirecting accordingly
                mAuth.signInWithEmailAndPassword(String.valueOf(email), password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomePage.class));
                            progressBar.setVisibility(View.INVISIBLE);
                        } else {
                            Toast.makeText(MainActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                    }
                });

            }
        });

        // re directing to sign up page
        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(MainActivity.this, SignUp.class);
                MainActivity.this.startActivity(activityChangeIntent);

            }
        });

        // sending email to reset password
        mForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// creating popup message that asks for email
                final EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
                passwordResetDialog.setView(resetMail);
// sending email after yes is selected
                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String mail = resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this, "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Reset Link is Not Sent " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
// returning back to profile management should no be clicked
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
// showing message created in this method
                passwordResetDialog.create().show();

            }
        });
    }
}


// Bellow is code for authenticating with facebook and google Was working.However wasn't writing a profile to the firebase realtime database so couldn't use for a number of application functions sake.


// Initialising Facebook SDK
//     FacebookSdk.sdkInitialize(MainActivity.this);


//        // Initialize Facebook Login button
//        mCallbackManager = CallbackManager.Factory.create();
//        loginButton = findViewById(R.id.btFBlogin_button);
//        loginButton.setReadPermissions("email", "public_profile");
//        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                handleFacebookAccessToken(loginResult.getAccessToken());
//
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });



//        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
//
//        signInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                signIn();
//                if (mAuth.getCurrentUser() != null) {
//                    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Email");
//                    Query query = dbref.orderByChild("Email").equalTo(mAuth.getCurrentUser().getEmail());
//                    query.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            if (!dataSnapshot.exists()) {
//                                mAuth.createUserWithEmailAndPassword(mAuth.getCurrentUser().getEmail(), "password").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<AuthResult> task) {
//                                        if (task.isSuccessful()) {
//                                            String userID = mAuth.getCurrentUser().getUid();
//                                            DocumentReference documentReference = fStore.collection("Users").document(userID);
//                                            User user = new User(String.valueOf(mAuth.getCurrentUser().getDisplayName().trim()), String.valueOf(mAuth.getCurrentUser().getPhoneNumber()), String.valueOf(mAuth.getCurrentUser().getEmail().trim()), "0", "0");
//                                            FirebaseDatabase.getInstance().getReference("Users")
//                                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                @Override
//                                                public void onComplete(@NonNull Task<Void> task) {
//                                                    if (task.isSuccessful()) {
//                                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                                                    } else {
//                                                        Toast.makeText(MainActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
//
//                                                    }
//                                                }
//                                            });
//
//                                        } else {
//                                            Toast.makeText(MainActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
//
//
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//                            throw databaseError.toException(); // don't ignore errors
//                        }
//                    });
//                    Intent intent = new Intent(MainActivity.this, HomePage.class);
//                    startActivity(intent);
//
//                } else {
//                    Toast.makeText(MainActivity.this, "failed to sign in", Toast.LENGTH_SHORT).show();
//
//
//                }
//
//
//            }
//        });
//
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null){
//            updateUI(currentUser);
//        }
//        updateUI(currentUser);
//    }
//
//    private void handleFacebookAccessToken(AccessToken token) {
//        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Toast.makeText(MainActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
//                        }
//
//                        // ...
//                    }
//                });
//    }
//
//
//    private void signIn() {
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, resultCodeSI);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        mCallbackManager.onActivityResult(requestCode, resultCode, data);
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == resultCodeSI) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }
//    }
//
//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//            Toast.makeText(MainActivity.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
//            FirebaseGoogleAuth(account);
//            Intent intent = new Intent(this, HomePage.class);
//            startActivity(intent);
//        } catch (ApiException e) {
//            Toast.makeText(MainActivity.this, "Signed In Failed Try Again", Toast.LENGTH_SHORT).show();
//            FirebaseGoogleAuth(null);
//        }
//
//    }
//
//    private void FirebaseGoogleAuth(GoogleSignInAccount acct) {
//        AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
//        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
//                    FirebaseUser user = mAuth.getCurrentUser();
//                    updateUI(user);
//
//                } else {
//                    Toast.makeText(MainActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
//                    updateUI(null);
//                }
//            }
//        });
//    }
//
//
//    private void updateUI(FirebaseUser user) {
//
//            if (user != null) {
//                Intent intent =new Intent (MainActivity.this,HomePage.class);
//                startActivity(intent);
//
//        }else {
//                Toast.makeText(this, "failed to sign in", Toast.LENGTH_SHORT).show();
//
//
//
//        }
//
//
//    }
//
//}






