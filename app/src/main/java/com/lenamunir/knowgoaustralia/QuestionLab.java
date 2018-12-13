package com.lenamunir.knowgoaustralia;

public class QuestionLab {

    private int mChoices [][] = {
            {R.string.q1_choice1, R.string.q1_choice2, R.string.q1_choice3, R.string.q1_choice4},
            {R.string.q2_choice1, R.string.q2_choice2, R.string.q2_choice3, R.string.q2_choice4},
            {R.string.q3_choice1, R.string.q3_choice2, R.string.q3_choice3, R.string.q3_choice4}

    };

    private int mCorrectAnswers [] = {
      R.string.q1_choice2, R.string.q2_choice1, R.string.q3_choice2};

    public int getChoice1 (int a){
        int choice0 = mChoices [a] [0];
        return choice0;
    }
    public int getChoice2 (int a){
        int choice1 = mChoices [a] [1];
        return choice1;
    }
    public int getChoice3 (int a){
        int choice2 = mChoices [a] [2];
        return choice2;
    }
    public int getChoice4 (int a){
        int choice3 = mChoices [a] [3];
        return choice3;
    }
    public int getCorrectAnswer (int a) {
        int answer = mCorrectAnswers [a];
        return answer;
    }

}
