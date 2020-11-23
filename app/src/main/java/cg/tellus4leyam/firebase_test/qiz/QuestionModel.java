package cg.tellus4leyam.firebase_test.qiz;


import android.util.Log;

import java.util.LinkedList;
import java.util.List;

//question model class
public class QuestionModel {

    public String prompt = "";
    public String response = "";
    public List<String> suggestion = new LinkedList<String>();


    public QuestionModel() {

    }


    public QuestionModel(String prompt, String response, List<String> suggestion) {

        Log.e("test", "QuestionModel: "+prompt+" "+response);

        this.prompt = prompt;
        this.response = response;
        this.suggestion = suggestion;
    }



}
