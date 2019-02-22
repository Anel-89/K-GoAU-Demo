package com.lenamunir.knowgoaustralia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lenamunir.knowgoaustralia.QuizContract.QuizTable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "quizAU.db";
    private static final int VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE "
         + QuizTable.TABLE_NAME + "(" +
        QuizTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizTable.COLUMN_QUEST + " TEXT, " +
                QuizTable.COLUMN_IMAGE + " INTEGER, " +
                QuizTable.COLUMN_ANS_1 + " TEXT, " +
                QuizTable.COLUMN_ANS_2 + " TEXT, " +
                QuizTable.COLUMN_ANS_3 + " TEXT, " +
                QuizTable.COLUMN_ANS_4 + " TEXT, " +
                QuizTable.COLUMN_FACT + " TEXT, " +
                QuizTable.COLUMN_CORRECT_ANS + " TEXT" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        // once db is created fill it with questions
        fillQuestionsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizTable.TABLE_NAME);
        onCreate(db);

    }

    private void fillQuestionsTable(){
//instance of QuestionBank
        QuestionBank q01 = new QuestionBank(
                R.string.q1, R.drawable.wallaman_falls,
                R.string.q1_choice1, R.string.q1_choice2, R.string.q1_choice3, R.string.q1_choice4,
                R.string.q1_choice2, // correct answer
                R.string.q1_fact);
        addQuestionsInDb(q01);
        QuestionBank q02 = new QuestionBank(
                R.string.q2, R.drawable.mount_kosciusko,
                R.string.q2_choice1, R.string.q2_choice2, R.string.q2_choice3, R.string.q2_choice4,
                R.string.q2_choice1,
                R.string.q2_fact);
        addQuestionsInDb(q02);
        QuestionBank q03 = new QuestionBank(R.string.q3, R.drawable.aboriginal_language,
                R.string.q3_choice1, R.string.q3_choice2, R.string.q3_choice3, R.string.q3_choice4,
                R.string.q3_choice2,
                R.string.q3_fact);
        addQuestionsInDb(q03);
        QuestionBank q04 = new QuestionBank(R.string.q4, R.drawable.mighty_murray,
                R.string.q4_choice1, R.string.q4_choice2, R.string.q4_choice3, R.string.q4_choice4,
                R.string.q4_choice4,
                R.string.q4_fact);
        addQuestionsInDb(q04);
        QuestionBank q05 = new QuestionBank(R.string.q5, R.drawable.lake_eyre_5,
                R.string.q5_choice1, R.string.q5_choice2, R.string.q5_choice3, R.string.q5_choice4,
                R.string.q5_choice1,
                R.string.q5_fact);
        addQuestionsInDb(q05);
    }
    private void addQuestionsInDb(QuestionBank questionBank){
        ContentValues cv = new ContentValues();
        cv.put(QuizTable.COLUMN_QUEST, questionBank.getQText());
        cv.put(QuizTable.COLUMN_IMAGE, questionBank.getQImg());
        cv.put(QuizTable.COLUMN_ANS_1, questionBank.getChoice1());
        cv.put(QuizTable.COLUMN_ANS_2, questionBank.getChoice2());
        cv.put(QuizTable.COLUMN_ANS_3, questionBank.getChoice3());
        cv.put(QuizTable.COLUMN_ANS_4, questionBank.getChoice4());
        cv.put(QuizTable.COLUMN_CORRECT_ANS, questionBank.getCorrectAnswer());
        cv.put(QuizTable.COLUMN_FACT, questionBank.getFact());

        db.insert(QuizTable.TABLE_NAME, null, cv);
    }

    public List<QuestionBank> getAllQuestions(){
        List<QuestionBank> questionList = new ArrayList<>();
        //ref. the db to get the data out of there
        //the first time this method is called, it also calls for OnCreate() which creates db
        db = getReadableDatabase();
        //now I need a Cursor to query the db
        Cursor cursor = db.rawQuery("SELECT * FROM " + QuizTable.TABLE_NAME, null);

        if (cursor.moveToFirst()){
            do{
                QuestionBank question = new QuestionBank();
                //now fill this QuestionBank object with the data of this raw
                question.setQText(cursor.getInt(cursor.getColumnIndex(QuizTable.COLUMN_QUEST)));
                question.setQImg(cursor.getInt(cursor.getColumnIndex(QuizTable.COLUMN_IMAGE)));
                question.setChoice1(cursor.getInt(cursor.getColumnIndex(QuizTable.COLUMN_ANS_1)));
                question.setChoice2(cursor.getInt(cursor.getColumnIndex(QuizTable.COLUMN_ANS_2)));
                question.setChoice3(cursor.getInt(cursor.getColumnIndex(QuizTable.COLUMN_ANS_3)));
                question.setChoice4(cursor.getInt(cursor.getColumnIndex(QuizTable.COLUMN_ANS_4)));
                question.setCorrectAnswer(cursor.getInt(cursor.getColumnIndex(QuizTable.COLUMN_CORRECT_ANS)));
                question.setFact(cursor.getInt(cursor.getColumnIndex(QuizTable.COLUMN_FACT)));

                questionList.add(question);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }

}
