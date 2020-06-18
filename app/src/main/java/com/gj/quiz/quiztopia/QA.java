package com.gj.quiz.quiztopia;

/**
 * Created by Gene on 3/2/18.
 */

public class QA {
    private int mQuestionID;
    private int mAnswerID;

    public QA(int questionID, int answerID) {
        mQuestionID = questionID;
        mAnswerID = answerID;
    }

    public int getQuestionID() {
        return mQuestionID;
    }

    public void setQuestionID(int questionID) {
        mQuestionID = questionID;
    }

    public int getAnswerID() {
        return mAnswerID;
    }

    public void setAnswerID(int answerID) {
        mAnswerID = answerID;
    }
}
