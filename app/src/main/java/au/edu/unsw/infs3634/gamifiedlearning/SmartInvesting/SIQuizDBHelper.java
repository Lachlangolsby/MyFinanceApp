package au.edu.unsw.infs3634.gamifiedlearning.SmartInvesting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import au.edu.unsw.infs3634.gamifiedlearning.SmartInvesting.SIQuizContract.*;



public class SIQuizDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SmartInvestingDB";
    private static final int DATABASE_VERSION = 3;

    private SQLiteDatabase db;

    public SIQuizDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
            onCreate(db);
        }
        private void fillQuestionsTable(){
            SIQuizQuestions q1 = new SIQuizQuestions("Which one of these is not a golden rule of investing? ", "Pay off your debts first",
                    "Have emergency savings ", "Research different asset classes","Invest at the highest rates of return", 4);
            addQuestion(q1);
            SIQuizQuestions q2 = new SIQuizQuestions("When Reviewing your finances, which of these does not classify as an asset",
                    "Superannuation", "Rented Property", "Car","None of the above", 2);
            addQuestion(q2);
            SIQuizQuestions q3 = new SIQuizQuestions("What is classified as a long term financial goal?", "1 year", "2-5 years", "6 months","5 + years", 4);
            addQuestion(q3);
            SIQuizQuestions q4 = new SIQuizQuestions("What is currency Risk?", "a risk only associated with foreign equities",
                    "Currency movements impact your investment and returns", "Risk associated with domestic currency assets","All of the above", 2);
            addQuestion(q4);
            SIQuizQuestions q5 = new SIQuizQuestions("Out of these assets which traditionally is riskier?", "Shares", "Property", "Options","Government Bonds", 3);
            addQuestion(q5);
        }
        private void addQuestion(SIQuizQuestions question) {
            ContentValues cv = new ContentValues();
            cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
            cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
            cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
            cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
            cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
            cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
            db.insert(QuestionsTable.TABLE_NAME, null, cv);
        }

    public List<SIQuizQuestions> getAllQuestions() {
        List<SIQuizQuestions> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                SIQuizQuestions question = new SIQuizQuestions();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}