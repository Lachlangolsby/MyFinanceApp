package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BadgesPage extends AppCompatActivity {
    ImageView mivSI3,mivSI5,mivFG3,mivFG5,mivMember;
    TextView mtvSI3,mtvSI5,mtvFG3,mtvFG5,mtvMember;
    private FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badges_page);
        mivFG3 = findViewById(R.id.ivFG3);
        mivFG5 = findViewById(R.id.ivFG5);
        mivSI3 = findViewById(R.id.ivSI3);
        mivSI5 = findViewById(R.id.ivSI5);
        mivMember= findViewById(R.id.ivMembers);
        mtvFG3 = findViewById(R.id.tvFG3);
        mtvFG5 = findViewById(R.id.tvFG5);
        mtvSI3 = findViewById(R.id.tvSI3);
        mtvSI5 = findViewById(R.id.tvSI5);
        mtvMember= findViewById(R.id.tvMembers);

        mivFG3.setAlpha(75);
        mivFG5.setAlpha(75);
        mivSI3.setAlpha(75);
        mivSI5.setAlpha(75);
        mivMember.setAlpha(75);
        fAuth = FirebaseAuth.getInstance();
        MembersBadge();
        FG3badge();
        FG5badge();
        SI3badge();
        SI5badge();



    }

    public void MembersBadge(){
       if (fAuth.getCurrentUser() != null){
           mivMember.setAlpha(255);
           mtvMember.setTextColor(Color.parseColor("#000000"));

       }
    }
    public void FG3badge(){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String userid =user.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                    String result = userProfile.FGSScore;
                    if (Integer.parseInt(result)> 2.5){
                        mivFG3.setAlpha(255);
                        mtvFG3.setTextColor(Color.parseColor("#000000"));

                    }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        }
    public void FG5badge(){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String userid =user.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                String result = userProfile.FGSScore;
                if (Integer.parseInt(result) == 5){
                    mivFG5.setAlpha(255);
                    mtvFG5.setTextColor(Color.parseColor("#000000"));

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void SI3badge(){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String userid =user.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                String result = userProfile.SIScore;
                if (Integer.parseInt(result)> 2.5){
                    mivSI3.setAlpha(255);
                    mtvSI3.setTextColor(Color.parseColor("#000000"));

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void SI5badge(){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String userid =user.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                String result = userProfile.SIScore;
                if (Integer.parseInt(result) == 5){
                    mivSI5.setAlpha(255);
                    mtvSI5.setTextColor(Color.parseColor("#000000"));

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    }

