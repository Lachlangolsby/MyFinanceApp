package au.edu.unsw.infs3634.gamifiedlearning.Notes;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface NoteDao {
    @Query("SELECT * FROM "+ Constants.TABLE_NAME_NOTE)
    List<Note> getNotes();

    @Insert
    long insertNote(Note note);


    @Update
    void updateNote(Note repos);


    @Delete
    void deleteNote(Note note);


    @Delete
    void deleteNotes(Note... note);



}

