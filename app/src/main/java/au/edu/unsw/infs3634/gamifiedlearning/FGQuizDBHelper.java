package au.edu.unsw.infs3634.gamifiedlearning;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static au.edu.unsw.infs3634.gamifiedlearning.FGQuizContract.*;

public class FGQuizDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "FinancialGoalSettingDB";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public FGQuizDBHelper(Context context) {
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
        FGQuizQuestions q1 = new FGQuizQuestions("which of these are not a question you'd ask yourself when you're assessing your income? ", "How often do you get paid?",
                "What day do you get paid?", "What day do you get paid?","What bank you get paid from?", 4);
        addQuestion(q1);
        FGQuizQuestions q2 = new FGQuizQuestions("What is a financial need?",
                "Goods and services that are required to survive and function in society", "goods and services that you desire to have and are not essential",
                "services that are required for a sustainable future","None of the above", 1);
        addQuestion(q2);
        FGQuizQuestions q3 = new FGQuizQuestions("Which of these is the least effective way to save money?", "Socialising with friends using public services and goods",
                "Buying in bulk", "Using a Woolworths everyday reward card when shopping","Skipping breakfast", 4);
        addQuestion(q3);
        FGQuizQuestions q4 = new FGQuizQuestions("What is an emergency fund", "a risky emergency investment that will offset your loses",
                "A savings pool for unforeseen circumstances", "An alternative name for a must buy share","None of the above", 2);
        addQuestion(q4);
        FGQuizQuestions q5 = new FGQuizQuestions("Which of these are an effective place to seek financial advice? ", "A financial Advisor", "Moneysmart.org", "The national debt helpline","All of the above", 4);
        addQuestion(q5);
    }
    private void addQuestion(FGQuizQuestions question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<FGQuizQuestions> getAllQuestions() {
        List<FGQuizQuestions> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + FGQuizContract.QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                FGQuizQuestions question = new FGQuizQuestions();
                question.setQuestion(c.getString(c.getColumnIndex(FGQuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(FGQuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(FGQuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(FGQuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(FGQuizContract.QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(FGQuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}

