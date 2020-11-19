package au.edu.unsw.infs3634.gamifiedlearning;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import au.edu.unsw.infs3634.gamifiedlearning.adapters.NotesAdapter;

public class NoteListActivity extends AppCompatActivity implements NotesAdapter.OnNoteItemClick {

    private TextView textViewMsg;
    private RecyclerView recyclerView;
    private NoteDataBase noteDataBase;
    private List<Note> notes;
    private NotesAdapter notesAdapter;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        initializeViews();
        displayList();
    }


private void displayList(){
        noteDataBase = NoteDataBase.getInstance(NoteListActivity.this);
        new RetrieveTask(this).execute();
}
private static class RetrieveTask extends AsyncTask<Void,Void, List<Note>>{
private WeakReference<NoteListActivity> activityReference;

      RetrieveTask(NoteListActivity context){
          activityReference = new WeakReference<>(context);
      }

    @Override
    protected List<Note> doInBackground(Void... voids) {
        if (activityReference.get()!= null){
            return activityReference.get().noteDataBase.getNoteDao().getNotes();
        }else
          return null;
    }
    @Override
    protected void onPostExecute(List<Note> notes) {
          if (notes != null &&  notes.size() >0 ) {
              activityReference.get().notes.clear();
              String user= FirebaseAuth.getInstance().getCurrentUser().getUid();
              System.out.println(notes);
              ArrayList<Note> checkednotes = new ArrayList<>();
             for (int i = 0; i < notes.size(); i++){
                 if (notes.get(i).getUID().trim().equals(user.trim())){
                     checkednotes.add(notes.get(i));
                     i++;
                 }else{i++;
                 }
             }
              System.out.println(checkednotes);
             activityReference.get().notes.addAll(checkednotes);
              activityReference.get().textViewMsg.setVisibility(View.GONE);
              activityReference.get().notesAdapter.notifyDataSetChanged();
          }
    }
}
private void initializeViews(){
    textViewMsg =findViewById(R.id.tv__empty);
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(listener);

    recyclerView = findViewById(R.id.recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(NoteListActivity.this));
    notes = new ArrayList<>();
    notesAdapter = new NotesAdapter(notes, NoteListActivity.this);
    recyclerView.setAdapter(notesAdapter);
}

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivityForResult(new Intent(NoteListActivity.this, AddNoteActivity.class), 100);
        }
    };

    @SuppressLint("MissingSuperCall")
    @Override
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

    @Override
    public void onNoteClick(final int pos) {
        //Alert Dialog asking user for update or delete notes
        new AlertDialog.Builder(NoteListActivity.this)
                .setItems(new String[]{"Delete", "Update"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                noteDataBase.getNoteDao().deleteNote(notes.get(pos));
                                notes.remove(pos);
                                listVisibility();
                                break;
                            case 1:
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

    private void listVisibility() {
        int emptyMsgVisibility = View.GONE;
        if (notes.size() == 0) {
            if (textViewMsg.getVisibility() == View.GONE)
                emptyMsgVisibility = View.VISIBLE;
        }
        textViewMsg.setVisibility(emptyMsgVisibility);
        notesAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        noteDataBase.cleanUp();
        super.onDestroy();
    }
}