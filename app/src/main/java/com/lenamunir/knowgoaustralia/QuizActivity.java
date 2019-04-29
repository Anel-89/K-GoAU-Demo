package com.lenamunir.knowgoaustralia;

import android.graphics.Color;
import android.os.Handler;
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

public class QuizActivity extends AppCompatActivity {

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
    private TextView mCountView;
    private RatingBar mRatingBar;


    private List<QuestionBank> mQuestionList;
    private int mQuestCount;
    private int mTotalQuestions;
    private QuestionBank mCurrentQuestion;
    private int mScore;
    private boolean mIsAnswered;
    private int mCorrectAnswer;
    private View myAnswerView;
    private static int TIME_OUT = 10;
    private int countDown;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        setContentView(R.layout.activity_quiz);

        mQuestionText = findViewById(R.id.question_text);
        mQuestionCount = findViewById(R.id.question_count);
        mCountView = findViewById(R.id.count_down);

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

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            }
        });


    }// onCreate() finishes here

    private final Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            countDown--;
            onTimerUpdate();
            if (countDown != 0) {
                startTimer();
            } else {
                onAnswerTimedOut();
            }
        }
    };
    View.OnClickListener answerChecker = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (myAnswerView != null) {
                // previous answer
                if (myAnswerView == view) {
                    // clicked on  same answer unselect it
                    myAnswerView = null;
                    view.setBackgroundColor(getResources().getColor(R.color.colorButtonDefault));
                } else {
                    // cliked on new answer
                    myAnswerView.setBackgroundColor(getResources().getColor(R.color.colorButtonDefault));
                    myAnswerView = view;
                    myAnswerView.setBackgroundColor(getResources().getColor(R.color.colorButtonSelectedAns));
                }
            } else {
                // no previous answer
                myAnswerView = view;
                myAnswerView.setBackgroundColor(getResources().getColor(R.color.colorButtonSelectedAns));
            }


        }
    };

    private void enableAnswering() {
        mChoice1.setOnClickListener(answerChecker);
        mChoice2.setOnClickListener(answerChecker);
        mChoice3.setOnClickListener(answerChecker);
        mChoice4.setOnClickListener(answerChecker);
    }

    private void disableAnswering() {
        mChoice1.setOnClickListener(null);
        mChoice2.setOnClickListener(null);
        mChoice3.setOnClickListener(null);
        mChoice4.setOnClickListener(null);

    }

    private void onAnswerTimedOut() {
        disableAnswering();
        checkAnswer();
    }

    private void startTimer() {
        mHandler.postDelayed(timerRunnable, 1000);
    }

    private void stopTimer() {
        mHandler.removeCallbacks(timerRunnable);
    }

    private void onTimerUpdate() {
        mCountView.setText("" + countDown);
    }

    private void checkAnswer() {
        if (myAnswerView != null) {
            // has answer
            if (myAnswerView.getTag() != null && myAnswerView.getTag().equals(mCorrectAnswer)) {
                //correct answer
                myAnswerView.setBackgroundColor(getResources().getColor(R.color.colorButtonCorrectAns));
                mDetailedAnswerView.setVisibility(View.VISIBLE);
                mRatingBar.setVisibility(View.VISIBLE);
                mNextButton.setEnabled(true);
                mScore++;
                mScoreTextView.setText("Score: " + mScore);
            } else {
                // wrong answer
                myAnswerView.setBackgroundColor(getResources().getColor(R.color.colorButtonWrongAns));
                mDetailedAnswerView.setVisibility(View.VISIBLE);
                mRatingBar.setVisibility(View.VISIBLE);
                mNextButton.setEnabled(true);

            }
        } else {
            //no answers selected
            mDetailedAnswerView.setVisibility(View.VISIBLE);
            mRatingBar.setVisibility(View.VISIBLE);
            mNextButton.setEnabled(true);
        }


    }

    private void updateQuestion() {
        myAnswerView = null;
        countDown = TIME_OUT;
        if (mQuestCount < mTotalQuestions) {
            mCurrentQuestion = mQuestionList.get(mQuestCount);

            mChoice1.setBackgroundColor(getResources().getColor(R.color.colorSecondary)); // try like this to bring back to default color
            mChoice2.setBackgroundColor(getResources().getColor(R.color.colorSecondary));
            mChoice3.setBackgroundColor(getResources().getColor(R.color.colorSecondary));
            mChoice4.setBackgroundColor(getResources().getColor(R.color.colorSecondary));

            int qText = mCurrentQuestion.getQText();
            CharSequence text = getText(qText);
            mQuestionText.setText(text);
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

            onTimerUpdate();
            startTimer();
            enableAnswering();

        } else {
            if (mQuestCount == mTotalQuestions) {
                mNextButton.setText(R.string.finish_bttn);
            }
            finishQuiz();
        }
    }

    private void finishQuiz() {
        finish();
    }
}
