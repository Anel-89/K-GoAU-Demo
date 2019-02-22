package com.lenamunir.knowgoaustralia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private View mDetailedAnswerView;
    private TextView mFact;


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
                if(!mIsAnswered){
                    if (mChoice1.isSelected() || mChoice2.isSelected() || mChoice3.isSelected() ||
                            mChoice4.isSelected()){


                    }else{
                        Toast.makeText(QuizActivity.this, "Please select an Answer", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    updateQuestion();
                }


            }
        });
        View.OnClickListener answerChecker = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getTag() != null && view.getTag().equals(mCorrectAnswer)) {
                    Toast.makeText(QuizActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                }
                mDetailedAnswerView.setVisibility(View.VISIBLE);
            }
        };
        mChoice1.setOnClickListener(answerChecker);
        mChoice2.setOnClickListener(answerChecker);
        mChoice3.setOnClickListener(answerChecker);
        mChoice4.setOnClickListener(answerChecker);
            }


    private void updateQuestion() {

        if (mQuestCount < mTotalQuestions){
            mCurrentQuestion = mQuestionList.get(mQuestCount);

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

        }else{
          finishQuiz();
        }

//        QuestionBank questionBank = questionArray[0];
//        mQuestionImage.setImageResource(questionBank.getQImg());
//        mQuestionText.setText(questionBank.getQText());
//        mFact.setText(questionBank.getFact());
//        mChoice1.setText(questionBank.getChoice1());
//        mChoice2.setText(questionBank.getChoice2());
//        mChoice3.setText(questionBank.getChoice3());
//        mChoice4.setText(questionBank.getChoice4());
//        mChoice1.setTag(questionBank.getChoice1());
//        mChoice2.setTag(questionBank.getChoice2());
//        mChoice3.setTag(questionBank.getChoice3());
//        mChoice4.setTag(questionBank.getChoice4());
//        // the previous code was returning wrong id for correct ans
//        mAnswer = questionBank.getCorrectAnswer();

//        mQuestionNumber++;

    }


    private void finishQuiz(){
        finish();
    }



    //Create a question object that contains
    // a question text, picture(if applicable), 4 answer options, correct answer & related fact

//    QuestionBank q01 = new QuestionBank(
//            R.string.q1, R.drawable.wallaman_falls,
//            R.string.q1_choice1, R.string.q1_choice2, R.string.q1_choice3, R.string.q1_choice4,
//            R.string.q1_choice2, // correct answer
//            R.string.q1_fact);
//    QuestionBank q02 = new QuestionBank(
//            R.string.q2, R.drawable.mount_kosciusko,
//            R.string.q2_choice1, R.string.q2_choice2, R.string.q2_choice3, R.string.q2_choice4,
//            R.string.q2_choice1,
//            R.string.q2_fact);
//    QuestionBank q03 = new QuestionBank(R.string.q3, R.drawable.aboriginal_language,
//            R.string.q3_choice1, R.string.q3_choice2, R.string.q3_choice3, R.string.q3_choice4,
//            R.string.q3_choice2,
//            R.string.q3_fact);
//    QuestionBank q04 = new QuestionBank(R.string.q4, R.drawable.mighty_murray,
//            R.string.q4_choice1, R.string.q4_choice2, R.string.q4_choice3, R.string.q4_choice4,
//            R.string.q4_choice4,
//            R.string.q4_fact);
//
//    QuestionBank[] questionArray = new QuestionBank[]{
//            q01, q02, q03, q04
//    };
//
//    public void shuffleQuestions() {
//        Collections.shuffle(Arrays.asList(questionArray));
//    }
}
