package au.edu.unsw.infs3634.gamifiedlearning.Notes;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import au.edu.unsw.infs3634.gamifiedlearning.BadgesPage;
import au.edu.unsw.infs3634.gamifiedlearning.FinCalc;
import au.edu.unsw.infs3634.gamifiedlearning.HomePage;
import au.edu.unsw.infs3634.gamifiedlearning.ProfileManagement;
import au.edu.unsw.infs3634.gamifiedlearning.QuizTopicSelection;
import au.edu.unsw.infs3634.gamifiedlearning.R;
import au.edu.unsw.infs3634.gamifiedlearning.SignUp.MainActivity;
import au.edu.unsw.infs3634.gamifiedlearning.SmartFinancialGoalSetting.FinancialGoalSetting;
import au.edu.unsw.infs3634.gamifiedlearning.SmartInvesting.SmartInvesting;

public class NoteListActivity extends AppCompatActivity implements NotesAdapter.OnNoteItemClick {
    // variables to be used within the class
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    private TextView textViewMsg;
    private RecyclerView recyclerView;
    private NoteDataBase noteDataBase;
    private List<Note> notes;
    private NotesAdapter notesAdapter;
    private int pos;
    // method for when a note is selected
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivityForResult(new Intent(NoteListActivity.this, AddNoteActivity.class), 100);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // loading correct xml
        setContentView(R.layout.activity_note_list);

        // calling methods to display correct notes
        initializeViews();
        displayList();


        // attaching nav menu to correct xml elements
        navigationView = findViewById(R.id.nav_View);
        drawerLayout = findViewById(R.id.notesDrawer);

        // action for when menu is open and closed
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // nav menu logic
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mProfile:
                        Toast.makeText(NoteListActivity.this, "Profile page", Toast.LENGTH_SHORT);
                        Intent activityChangeIntent = new Intent(NoteListActivity.this, ProfileManagement.class);
                        NoteListActivity.this.startActivity(activityChangeIntent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mHome:
                        Toast.makeText(NoteListActivity.this, "Home", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentHome = new Intent(NoteListActivity.this, HomePage.class);
                        NoteListActivity.this.startActivity(activityChangeIntentHome);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule1:
                        Toast.makeText(NoteListActivity.this, "Module1", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentCalculator = new Intent(NoteListActivity.this, FinCalc.class);
                        NoteListActivity.this.startActivity(activityChangeIntentCalculator);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule2:
                        Toast.makeText(NoteListActivity.this, "Module2", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentSmartInvesting = new Intent(NoteListActivity.this, SmartInvesting.class);
                        NoteListActivity.this.startActivity(activityChangeIntentSmartInvesting);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule3:
                        Toast.makeText(NoteListActivity.this, "Module3", Toast.LENGTH_SHORT);
                        drawerLayout.closeDrawers();
                        Intent activityChangeIntentFG = new Intent(NoteListActivity.this, FinancialGoalSetting.class);
                        NoteListActivity.this.startActivity(activityChangeIntentFG);
                        break;
                    case R.id.mModule4:
                        Toast.makeText(NoteListActivity.this, "Module 4", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentQS = new Intent(NoteListActivity.this, QuizTopicSelection.class);
                        NoteListActivity.this.startActivity(activityChangeIntentQS);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule5:
                        Toast.makeText(NoteListActivity.this, "Module 5", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentB = new Intent(NoteListActivity.this, BadgesPage.class);
                        NoteListActivity.this.startActivity(activityChangeIntentB);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mModule6:
                        Toast.makeText(NoteListActivity.this, "Module 6", Toast.LENGTH_SHORT);
                        Intent activityChangeIntentN = new Intent(NoteListActivity.this, NoteListActivity.class);
                        NoteListActivity.this.startActivity(activityChangeIntentN);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mLogout:
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(NoteListActivity.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                        Intent activityChangeIntent2 = new Intent(NoteListActivity.this, MainActivity.class);
                        NoteListActivity.this.startActivity(activityChangeIntent2);
                        drawerLayout.closeDrawers();
                        break;
                }

                return false;
            }
        });
    }

    // return true or false about menu selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // display correct list of notes
    private void displayList() {
        noteDataBase = NoteDataBase.getInstance(NoteListActivity.this);
        new RetrieveTask(this).execute();
    }

    // initialising recyclerView
    private void initializeViews() {
        textViewMsg = findViewById(R.id.tv__empty);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(listener);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(NoteListActivity.this));
        notes = new ArrayList<>();
        notesAdapter = new NotesAdapter(notes, NoteListActivity.this);
        recyclerView.setAdapter(notesAdapter);
    }

    @SuppressLint("MissingSuperCall")
    @Override

    // add note to list should note be saved
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode > 0) {
            if (resultCode == 1) {
                notes.add((Note) data.getSerializableExtra("note"));
            } else if (resultCode == 2) {
                notes.set(pos, (Note) data.getSerializableExtra("note"));
            }
            listVisibility();
        }
    }

    // method for whether user wants to delete or update notes
    @Override
    public void onNoteClick(final int pos) {
        //Alert Dialog asking user for update or delete notes
        new AlertDialog.Builder(NoteListActivity.this)
                .setItems(new String[]{"Delete", "Update"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            // in case of delete, delete note
                            case 0:
                                noteDataBase.getNoteDao().deleteNote(notes.get(pos));
                                notes.remove(pos);
                                listVisibility();
                                break;
                            case 1:
                                // incase of update take user to note to be updated
                                NoteListActivity.this.pos = pos;
                                startActivityForResult(
                                        new Intent(NoteListActivity.this,
                                                AddNoteActivity.class).putExtra("note", notes.get(pos)),
                                        100);

                                break;
                        }
                    }
                }).show();

    }

    // Method decifering wether or not to display add notes message in the background of nots screen
    private void listVisibility() {
        int emptyMsgVisibility = View.GONE;
        if (notes.size() == 0) {
            if (textViewMsg.getVisibility() == View.GONE)
                emptyMsgVisibility = View.VISIBLE;
        }
        textViewMsg.setVisibility(emptyMsgVisibility);
        notesAdapter.notifyDataSetChanged();
    }

    // on destroy method to interact with db safely (clean up and close)
    @Override
    protected void onDestroy() {
        noteDataBase.cleanUp();
        if (noteDataBase.isOpen()) {
            noteDataBase.close();
        }
        super.onDestroy();
    }

    // class that retireves notes
    private static class RetrieveTask extends AsyncTask<Void, Void, List<Note>> {
        private WeakReference<NoteListActivity> activityReference;

        RetrieveTask(NoteListActivity context) {
            activityReference = new WeakReference<>(context);
        }

        // assess wether their are any notes in db
        @Override
        protected List<Note> doInBackground(Void... voids) {
            if (activityReference.get() != null) {
                return activityReference.get().noteDataBase.getNoteDao().getNotes();
            } else
                return null;
        }

        // if notes in db assess which notes belong to the current user via the email in both the firebase realtime db and the SQLite db.
        @Override
        protected void onPostExecute(List<Note> notes) {
            if (notes != null) {
                activityReference.get().notes.clear();
                String user = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                System.out.println(notes);
                ArrayList<Note> checkednotes = new ArrayList<>();
                // checking list to see if email variables match
                for (int i = 0; i < notes.size(); i++) {
                    if (notes.get(i).getEmail().trim().equals(user)) {
                        checkednotes.add(notes.get(i));
                    }
                }
                // assigning correct notes
                System.out.println(checkednotes);
                activityReference.get().notes.addAll(checkednotes);
                activityReference.get().textViewMsg.setVisibility(View.GONE);
                activityReference.get().notesAdapter.notifyDataSetChanged();
                ;

            }
        }
    }
}