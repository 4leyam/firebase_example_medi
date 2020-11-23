package cg.tellus4leyam.firebase_test.qiz;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuestionController {

    private static QuestionController instance = null;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();


    private static final String QUESTION_PATH = "questions";


    public static QuestionController getInstance() {
        if (instance == null) {
            instance = new QuestionController();
        }
        return instance;
    }


    private QuestionController() {

    }

    public ArrayList<QuestionModel> questionSet = new ArrayList<>();


    /**
     *
     * {"nfkjfne" , "jbhejfhef" , "bjbdjbdjbe"}
     *
     * root :
     *  -> questions :
     *          -> 5ffhb34739384 :
     *                  prompt : "quelle est la capitale de l'inde"
     *                  response : "new delhi"
     *                  suggestion : {"Brazzaville" , "kinshasa" , "paris" , "london"}
     *
     *          -> 5ffhb34739384 :
     *                         prompt : "quelle est la capitale de l'inde"
     *                         response : "new delhi"
     *                         suggestion : {"Brazzaville" , "kinshasa" , "paris" , "london"}
     *
     *          -> 5ffhb34739384 :
     *                         prompt : "quelle est la capitale de l'inde"
     *                         response : "new delhi"
     *                         suggestion : {"Brazzaville" , "kinshasa" , "paris" , "london"}
     *
     */
    public void readQuestionSet(final QuestionResponseHandler handler) {


        this.database.getReference(QUESTION_PATH).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                boolean wasEmpty = false;
                if(QuestionController.this.questionSet.isEmpty()) {
                    wasEmpty = true;
                }

                QuestionModel value = snapshot.getValue(QuestionModel.class);
                QuestionController.this.questionSet.add(value);

                if(wasEmpty) {
                    handler.onFirstQuestionReceived();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }



    public void readOneQuestion(final QuestionResponseHandler handler) {

        this.database.getReference(QUESTION_PATH).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                QuestionModel value = snapshot.getValue(QuestionModel.class);
                handler.onQuestionSubmitted(true , value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                handler.onQuestionSubmitted(false , null);
            }
        });

    }


    public void writeSingleQuestion(QuestionModel newQuestionModel) {



        final Task<Void> voidTask = this.database.getReference(QUESTION_PATH).push().setValue(newQuestionModel);
        voidTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                //operation has been done successfully


            }
        });
        voidTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                //failed to complete the operation.



            }
        });

    }


}

