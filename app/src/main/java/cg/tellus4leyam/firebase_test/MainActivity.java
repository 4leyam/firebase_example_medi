package cg.tellus4leyam.firebase_test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import cg.tellus4leyam.firebase_test.qiz.QuestionController;
import cg.tellus4leyam.firebase_test.qiz.QuestionModel;
import cg.tellus4leyam.firebase_test.qiz.QuestionResponseHandler;


class Randomizer {
    public static int generate(int min,int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}



public class MainActivity extends AppCompatActivity implements QuestionResponseHandler {

    Button optionOne, optionTwo;
    TextView question;

    private int currentQuestionIndex = 0;
    private QuestionController controller1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller1 = QuestionController.getInstance();
        controller1.readQuestionSet(this);


        //first write to the database.
//        QuestionModel newQuestionModel = new QuestionModel("Quelle est la capitale de la RDC" ,
//                "Kinshasa" ,
//
//                Arrays.asList("Brazzaville", "Paris",
//                        "London", "Kinshasa", "kigali")
//                );
//
//        controller1.writeSingleQuestion(newQuestionModel);

        this.optionOne = findViewById(R.id.suggestion_one);
        this.optionTwo = findViewById(R.id.suggestion_two);
        this.question = findViewById(R.id.question_text);







    }

    @Override
    public void onQuestionSubmitted(boolean isSuccess, @Nullable QuestionModel modelValue) {

    }

    @Override
    public void onFirstQuestionReceived() {

        ArrayList<QuestionModel> set = controller1.questionSet;

        if (!set.isEmpty()) {

            final QuestionModel questionModel = set.get(this.currentQuestionIndex);

            boolean correctRand = false;

            int randomIndex = Randomizer.generate(0,4);

            String s = questionModel.suggestion.get(randomIndex);
            if (questionModel.response.trim().equals(s))
                correctRand = true;

            this.optionOne.setText(s);

            randomIndex = Randomizer.generate(0,4);

            s = questionModel.suggestion.get(randomIndex);
            if (!correctRand)
                s = questionModel.response;


            this.optionTwo.setText(s);
            this.question.setText(questionModel.prompt);
        }

    }
}
