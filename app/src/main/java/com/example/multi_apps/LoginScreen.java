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

import org.w3c.dom.Text;

public class LoginScreen extends AppCompatActivity {
    EditText email,password;
    TextView createacct,forgotpwd;
    Button loginbtn;
    String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        createacct = (TextView) findViewById((R.id.createacc));
        forgotpwd = (TextView) findViewById(R.id.forgotpwd);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        createacct.setOnClickListener(v -> startActivity(new Intent(LoginScreen.this,Register.class)));
        loginbtn.setOnClickListener(v -> performLogin());

    }

    private void performLogin() {
        String e1 = email.getText().toString();
        String e2 = password.getText().toString();

        if(!e1.matches(emailpattern)){
            email.setError("Enter correct email");
        }
        else if(e2.isEmpty() || e2.length()<6){
            password.setError("Enter correct password");
        }
        else{
            progressDialog.setMessage("Please wait while login...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(e1,e2).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    sendUserToNextActivity();
                    Toast.makeText(LoginScreen.this, "Login Successfull!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(LoginScreen.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                }
            });
    }
}

    private void sendUserToNextActivity() {
        Intent intent = new Intent(LoginScreen.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}