package com.lenamunir.knowgoaustralia;

public class QuestionBank {

    private int mQText;
    private int mQImg;
    private int mChoice1;
    private int mChoice2;
    private int mChoice3;
    private int mChoice4;
    private int mCorrectAnswer;
    private int mFact;

    public QuestionBank() { }


    public QuestionBank(int QText, int QImg, int choice1, int choice2, int choice3, int choice4, int correctAnswer, int fact) {
        mQText = QText;
        mQImg = QImg;
        mChoice1 = choice1;
        mChoice2 = choice2;
        mChoice3 = choice3;
        mChoice4 = choice4;
        mCorrectAnswer = correctAnswer;
        mFact = fact;
    }

     public int getFact() {
        return mFact;
    }

    public int getQText() {
        return mQText;
    }

    public int getQImg() {
        return mQImg;
    }

    public int getChoice1() {
        return mChoice1;
    }

    public int getChoice2() {
        return mChoice2;
    }

    public int getChoice3() {
        return mChoice3;
    }

    public int getChoice4() {
        return mChoice4;
    }

    public int getCorrectAnswer() {
        return mCorrectAnswer;
    }


    public void setQText(int QText) {
        mQText = QText;
    }

    public void setQImg(int QImg) {
        mQImg = QImg;
    }

    public void setChoice1(int choice1) {
        mChoice1 = choice1;
    }

    public void setChoice2(int choice2) {
        mChoice2 = choice2;
    }

    public void setChoice3(int choice3) {
        mChoice3 = choice3;
    }

    public void setChoice4(int choice4) {
        mChoice4 = choice4;
    }

    public void setCorrectAnswer(int correctAnswer) {
        mCorrectAnswer = correctAnswer;
    }

    public void setFact(int fact) {
        mFact = fact;
    }
}