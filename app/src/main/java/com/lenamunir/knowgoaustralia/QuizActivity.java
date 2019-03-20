package com.lenamunir.knowgoaustralia;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity{

    private TextView mQuestionText;
    private ImageView mQuestionImage;
    private TextView mQuestionCount;
    private Button mChoice1;
    private Button mChoice2;
    private Button mChoice3;
    private Button mChoice4;

    private Button mNextButton;
    private LinearLayout mDetailedAnswerView;
    private TextView mFact;
    private TextView mScoreTextView;
    private RatingBar mRatingBar;


    private List<QuestionBank> mQuestionList;
    private int mQuestCount;
    private int mTotalQuestions;
    private QuestionBank mCurrentQuestion;
    private int mScore;
    private boolean mIsAnswered;
    private int mCorrectAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionText = findViewById(R.id.question_text);
        mQuestionCount = findViewById(R.id.question_count);

        mQuestionImage = findViewById(R.id.question_image);

        mNextButton = findViewById(R.id.next_button);
        mChoice1 = findViewById(R.id.bttn_ans_a);
        mChoice2 = findViewById(R.id.bttn_ans_b);
        mChoice3 = findViewById(R.id.bttn_ans_c);
        mChoice4 = findViewById(R.id.bttn_ans_d);
        mDetailedAnswerView = findViewById(R.id.detail_answer);
        mFact = findViewById(R.id.answer_detail_view);
        mScoreTextView = findViewById(R.id.score);
        mRatingBar = findViewById(R.id.ratingBar);



        QuizDbHelper quizDbHelper = new QuizDbHelper(this);
        //calling this method creates the database
        mQuestionList = quizDbHelper.getAllQuestions();

        mTotalQuestions = mQuestionList.size();
        //shuffle the list of questions
        Collections.shuffle(mQuestionList);

        updateQuestion();

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateQuestion();
                }
        });
        mNextButton.setEnabled(false);



        View.OnClickListener answerChecker = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getTag() != null && view.getTag().equals(mCorrectAnswer)) {
                    view.setBackgroundColor(getResources().getColor(R.color.colorButtonCorrectAns));
                    mDetailedAnswerView.setVisibility(View.VISIBLE);
                    mRatingBar.setVisibility(View.VISIBLE);
                    mNextButton.setEnabled(true);
                    mScore++;
                    mScoreTextView.setText("Score: " + mScore);


                } else {
                   view.setBackgroundColor(getResources().getColor(R.color.colorButtonWrongAns));

                    }
            }
        };
        mChoice1.setOnClickListener(answerChecker);
        mChoice2.setOnClickListener(answerChecker);
        mChoice3.setOnClickListener(answerChecker);
        mChoice4.setOnClickListener(answerChecker);

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            }
        });



        }// onCreate() finishes here


    private void updateQuestion() {

            if (mQuestCount < mTotalQuestions){
            mCurrentQuestion = mQuestionList.get(mQuestCount);

            mChoice1.setBackgroundColor(Color.TRANSPARENT); // try like this to bring back to default color
            mChoice2.setBackgroundColor(getResources().getColor(R.color.colorSecondary));
            mChoice3.setBackgroundColor(getResources().getColor(R.color.colorSecondary));
            mChoice4.setBackgroundColor(getResources().getColor(R.color.colorSecondary));

            mQuestionText.setText(mCurrentQuestion.getQText());
            mQuestionImage.setImageResource(mCurrentQuestion.getQImg());
            mChoice1.setText(mCurrentQuestion.getChoice1());
            mChoice2.setText(mCurrentQuestion.getChoice2());
            mChoice3.setText(mCurrentQuestion.getChoice3());
            mChoice4.setText(mCurrentQuestion.getChoice4());
            mFact.setText(mCurrentQuestion.getFact());
            mChoice1.setTag(mCurrentQuestion.getChoice1());
            mChoice2.setTag(mCurrentQuestion.getChoice2());
            mChoice3.setTag(mCurrentQuestion.getChoice3());
            mChoice4.setTag(mCurrentQuestion.getChoice4());

            mCorrectAnswer = mCurrentQuestion.getCorrectAnswer();

            mQuestCount++;
            mQuestionCount.setText(mQuestCount + "/" + mTotalQuestions);
            mIsAnswered = false;
            mDetailedAnswerView.setVisibility(View.GONE);
            mNextButton.setEnabled(false);
            mRatingBar.setRating(0);
            mRatingBar.setVisibility(View.GONE);


        }else{
                if (mQuestCount == mTotalQuestions){
                    mNextButton.setText(R.string.finish_bttn);
                }
          finishQuiz();
        }
    }

   private void finishQuiz(){
        finish();
    }
}
