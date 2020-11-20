package au.edu.unsw.infs3634.gamifiedlearning.Notes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = { Note.class }, version = 3, exportSchema = false)
@TypeConverters({DataRoomConverter.class})
public abstract class NoteDataBase extends RoomDatabase {
    public abstract NoteDao getNoteDao();

    private static NoteDataBase noteDB;

    public static NoteDataBase getInstance(Context context) {
        if (null == noteDB) {
            noteDB = buildDatabaseInstance(context);
        }
        return noteDB;
    }

    private static NoteDataBase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                NoteDataBase.class,
                Constants.DB_NAME)
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        noteDB = null;
    }

}