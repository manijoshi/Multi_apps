package com.example.multi_apps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    TextView haveaccount;
    EditText email,password,cpassword;
    Button register;
    String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        haveaccount = (TextView) findViewById(R.id.haveacct);
        email = (EditText) findViewById(R.id.email_reg);
        password = (EditText) findViewById(R.id.password_reg);
        cpassword = (EditText) findViewById(R.id.cpassword_reg);
        register = (Button) findViewById(R.id.register);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        haveaccount.setOnClickListener(v -> startActivity(new Intent(Register.this,LoginScreen.class)));

        register.setOnClickListener(v -> PerformAuth());
    }
    private void PerformAuth(){
        String e1 = email.getText().toString();
        String e2 = password.getText().toString();
        String e3 = cpassword.getText().toString();

        if(!e1.matches(emailpattern)){
            email.setError("Enter correct email");
        }
        else if(e2.isEmpty() || e2.length()<6){
            password.setError("Enter correct password");
        }
        else if(!e2.equals(e3)){
            cpassword.setError("Password mismatch");
        }
        else{
            progressDialog.setMessage("Please wait while registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(e1,e2).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    sendUserToNextActivity();
                    Toast.makeText(Register.this, "Registration Done!!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Register.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(Register.this,LoginScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}