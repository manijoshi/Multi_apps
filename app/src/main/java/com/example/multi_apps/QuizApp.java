package com.example.multi_apps;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Random;

public class QuizApp extends AppCompatActivity {
    private TextView questionTV, questionNumberTV;
    private Button option1Btn,option2Btn,option3Btn,option4Btn,mback;
    private ArrayList<QuizModal> quizModalArrayList;
    Random random;
    int currentScore = 0,questionAttempted = 1,currentPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_app);
        questionTV = (TextView)findViewById(R.id.idTVQuestion);
        questionNumberTV = (TextView)findViewById(R.id.idTVQuestionAttempted);
        option1Btn = (Button)findViewById(R.id.idBtnOption1);
        option2Btn = (Button)findViewById(R.id.idBtnOption2);
        option3Btn = (Button)findViewById(R.id.idBtnOption3);
        option4Btn = (Button)findViewById(R.id.idBtnOption4);
        mback = (Button)findViewById(R.id.back);
        quizModalArrayList = new ArrayList<>();
        random = new Random();
        getQuizQuestion(quizModalArrayList);
        currentPos = random.nextInt(quizModalArrayList.size());
        setDataToViews(currentPos);

        option1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option1Btn.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });
        option2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option2Btn.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });
        option3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option3Btn.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });
        option4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option4Btn.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });
        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizApp.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showBottomSheet(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(QuizApp.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_bottom_sheet,(LinearLayout)findViewById(R.id.idLLScore));
        TextView scoreTV = bottomSheetView.findViewById(R.id.idTVScore);
        Button restartQuizBtn = bottomSheetView.findViewById(R.id.idBtnRestart);
        scoreTV.setText("Your Score is \n"+currentScore+"/10");
        restartQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
                questionAttempted = 1;
                currentScore = 0;
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }

    private void setDataToViews(int currentPos) {
        questionNumberTV.setText("Questions Attempted : "+questionAttempted+"/10");
        if(questionAttempted == 10){
            showBottomSheet();
        }
        else{
            questionTV.setText(quizModalArrayList.get(currentPos).getQuestion());
            option1Btn.setText(quizModalArrayList.get(currentPos).getOption1());
            option2Btn.setText(quizModalArrayList.get(currentPos).getOption2());
            option3Btn.setText(quizModalArrayList.get(currentPos).getOption3());
            option4Btn.setText(quizModalArrayList.get(currentPos).getOption4());
        }

    }

    private void getQuizQuestion(ArrayList<QuizModal> quizModalArrayList) {
        quizModalArrayList.add(new QuizModal("Database is a _____","Organized collection of tables that cannot be accessed, updated, and managed","Organized collection of data that cannot be updated","Organized collection of data or information that can be accessed, updated and managed","Collection of data or information without organizing","Organized collection of data or information that can be accessed, updated, and managed"));
        quizModalArrayList.add(new QuizModal("Who created the DBMS?","Charles Bachman","Edgar Frank Codd","Charles Babbage","Sharon B. Codd","Charles Bachman"));
        quizModalArrayList.add(new QuizModal("Which type of data can be stored in the database?","Image oriented data","Data in the form of audio or video","Text, files containing data","All of the above","All of the above"));
        quizModalArrayList.add(new QuizModal("Which of the following is not a type of database?","Hierarchical database","Network database","Distributed database","Decentralized database","Decentralized database"));
        quizModalArrayList.add(new QuizModal("Which of the following is not an example of DBMS?","Microsoft Acess","MySQL","IBM DB2","Google","Google"));
        quizModalArrayList.add(new QuizModal("Which of the following is a component of the DBMS?","Data Languages","Data","Data Manager","All of the above","All of the above"));
        quizModalArrayList.add(new QuizModal("What is information about data in a database called?","Hyper data","Tera data","Meta data","Relations","Meta data"));
        quizModalArrayList.add(new QuizModal("______________ is a set of one or more attributes taken collectively to uniquely identify a record.","Primary Key of a database","Candidate key of a database","Super key of a database","Foreign key of a database","Super key of a database"));
        quizModalArrayList.add(new QuizModal("Which command is used to remove a relation from an SQL database?","Drop Table","Delete","Purge","Remove","Drop table"));
        quizModalArrayList.add(new QuizModal("Why does database design prevents some data from being represented?","Insertion anomalies of a data","Deletion anomalies of a data","Update anomalies of a data","None of the mentioned","Insertion anomalies of a data"));
        quizModalArrayList.add(new QuizModal(" _________________ operations do not preserve non-matched tuples.","Inner join","Natural join","Right outer join","left outer join","Inner join"));
        quizModalArrayList.add(new QuizModal("Which command is used to delete a database?","drop database database_name","drop database_name","Delete database database_name","Delete database_name","drop database database_name"));
        quizModalArrayList.add(new QuizModal("The oldest database model is _______________","Network database","Physical database","Hierarchical database","Relational database","Network database"));
        quizModalArrayList.add(new QuizModal("The transaction is made permanent by __________________ in the database.","Flashback database","Rollback database","Commit database","View","Commit database"));


    }
}