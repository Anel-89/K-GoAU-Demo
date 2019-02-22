package com.lenamunir.knowgoaustralia;

import android.provider.BaseColumns;

public final class QuizContract implements BaseColumns {
    private QuizContract() {}

    public static final class QuizTable{

        public static final String TABLE_NAME = "questions_table";
        public static final String COLUMN_QUEST = "question";
        public static final String COLUMN_ANS_1 = "ans_1";
        public static final String COLUMN_ANS_2 = "ans_2";
        public static final String COLUMN_ANS_3 = "ans_3";
        public static final String COLUMN_ANS_4 = "ans_4";
        public static final String COLUMN_CORRECT_ANS = "correct_ans";
        public static final String COLUMN_IMAGE = "question_image";
        public static final String COLUMN_FACT = "fact";
        public static final String _ID = "_id";

    }
}
