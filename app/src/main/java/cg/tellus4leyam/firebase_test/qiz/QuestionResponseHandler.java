package cg.tellus4leyam.firebase_test.qiz;

import androidx.annotation.Nullable;

public interface QuestionResponseHandler {

    void onQuestionSubmitted(boolean isSuccess, @Nullable QuestionModel modelValue);

    void onFirstQuestionReceived();

}
