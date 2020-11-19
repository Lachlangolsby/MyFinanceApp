package au.edu.unsw.infs3634.gamifiedlearning.Notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = Constants.TABLE_NAME_NOTE)
public class Note implements Serializable {
    // Creating Collumns
    @PrimaryKey(autoGenerate = true)
    private int note_id;

    @ColumnInfo
            (name = "notes_content")
    private String content;

    private String title;

    private Date date;

    @NonNull
    private String UID;

    // Creating the constructor
    public Note(String content, String title) {
       // this.note_id = note_id;
        this.content = content;
        this.title = title;
        this.date = new Date(System.currentTimeMillis());
        this.UID =  String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }


    // Creating Getters and Setters

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date Date) {
        this.date = Date;
    }

    @Override
    public boolean equals (@Nullable Object o){
        if (this ==o ) return true;

        if (!(o instanceof Note)) return false;

        Note note = (Note) o ;
        if (note_id != note.note_id) return false;
        return title != null ? title.equals(note.title): note.title == null;
    }

    @Override
    public int hashCode(){
        int result = (int) note_id;
        result = 31* result +(title!= null ? title.hashCode():0);
        return result;
    }
    @NonNull
    @Override
    public String toString(){
        return "Note{" +
                "Note_id=" + note_id +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", UID='" + UID + '\'' +
                '}';
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
// Refrernce: Video used to create notes page:https://www.youtube.com/watch?v=nOoJoPE6MsU&t=18s