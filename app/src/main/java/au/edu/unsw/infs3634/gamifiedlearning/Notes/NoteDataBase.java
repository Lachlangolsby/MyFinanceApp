package au.edu.unsw.infs3634.gamifiedlearning.Notes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

// room databse
@Database(entities = {Note.class}, version = 3, exportSchema = false)
@TypeConverters({DataRoomConverter.class})
public abstract class NoteDataBase extends RoomDatabase {
    private static NoteDataBase noteDB;

    // calling to build database if not existant
    public static NoteDataBase getInstance(Context context) {
        if (null == noteDB) {
            noteDB = buildDatabaseInstance(context);
        }
        return noteDB;
    }

    // building database
    private static NoteDataBase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                NoteDataBase.class,
                Constants.DB_NAME)
                .allowMainThreadQueries().build();
    }

    // using notes dao
    public abstract NoteDao getNoteDao();

    // clean database
    public void cleanUp() {
        noteDB = null;
    }

}