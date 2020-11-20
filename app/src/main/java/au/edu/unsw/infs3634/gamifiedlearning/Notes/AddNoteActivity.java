package au.edu.unsw.infs3634.gamifiedlearning.Notes;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.lang.ref.WeakReference;

import au.edu.unsw.infs3634.gamifiedlearning.R;

public class AddNoteActivity extends AppCompatActivity {
    // Variables & objects
    private TextInputEditText et_Title, et_Content;
    private boolean update ;
    private Button btSave;
    private NoteDataBase noteDB;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        et_Content = findViewById(R.id.et_content);
        et_Title = findViewById(R.id.et_title);
        btSave = findViewById(R.id.but_save);
        noteDB = NoteDataBase.getInstance(AddNoteActivity.this);

        if ((note = (Note) getIntent().getSerializableExtra( "note")) != null){
            getSupportActionBar().setTitle("Update Note");
            update = true;
            btSave.setText("Update");
            et_Title.setText(note.getTitle());
            et_Content.setText(note.getContent());
        }

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (update){
                    note.setContent(et_Content.getText().toString());
                    note.setTitle(et_Title.getText().toString());
                    noteDB.getNoteDao().updateNote(note);
                    setResult(note,2);

                }else{
                    note = new Note (et_Content.getText().toString(),et_Title.getText().toString());
                    new InsertTask(AddNoteActivity.this, note).execute();
                }

            }
        });

    }
    private void setResult(Note note, int flag){
        setResult(flag,new Intent().putExtra("note", note));
        finish();
    }
    private static class InsertTask extends AsyncTask<Void,Void,Boolean> {
        private WeakReference <AddNoteActivity> activityWeakReference;
        private Note note ;

        InsertTask( AddNoteActivity context, Note note){
            activityWeakReference = new WeakReference<>(context);
            this.note = note;
        }
         @Override
        protected Boolean doInBackground (Void... Voids){
            long j = activityWeakReference.get().noteDB.getNoteDao().insertNote(note);
            note.setNote_id((int) j);
            return true;
         }
         @Override
        protected void onPostExecute (Boolean aBoolean) {
            if (aBoolean){
                activityWeakReference.get().setResult(note,1);
                activityWeakReference.get().finish();
            }
         }
    }
}