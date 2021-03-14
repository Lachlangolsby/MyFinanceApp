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
    // Creating Columns for db
    @PrimaryKey(autoGenerate = true)
    private int note_id;

    @ColumnInfo
            (name = "notes_content")
    private String content;

    private String title;

    private Date date;

    @NonNull
    private String email;

    // Creating the constructor
    public Note(String content, String title) {
        this.content = content;
        this.title = title;
        this.date = new Date(System.currentTimeMillis());
        this.email = String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getEmail().trim());
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

    // overriding the default equals method for the db
    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;

        if (!(o instanceof Note)) return false;

        Note note = (Note) o;
        if (note_id != note.note_id) return false;
        return title != null ? title.equals(note.title) : note.title == null;
    }

    // overriding the default hash code method
    @Override
    public int hashCode() {
        int result = (int) note_id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    // over riding the default to sting method so db prints nicely
    @NonNull
    @Override
    public String toString() {
        return "Note{" +
                "Note_id=" + note_id +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", Email='" + email + '\'' +
                '}';
    }

    // more getter and setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

