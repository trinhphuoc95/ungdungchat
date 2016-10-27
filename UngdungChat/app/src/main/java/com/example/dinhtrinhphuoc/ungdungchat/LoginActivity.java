package com.example.dinhtrinhphuoc.ungdungchat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_LogInChat;
    private EditText txtEmail;
    private EditText txtPassword;
    private TextView textViewSignUp;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();



        progressDialog = new ProgressDialog(this);

        btn_LogInChat = (Button) findViewById(R.id.btn_LogInChat);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        textViewSignUp = (TextView) findViewById(R.id.textViewSignUp);

        btn_LogInChat.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);

    }

    private void userLogin() {
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter Email !", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter Password !", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Logining Please wait... ");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            //start activity tiep
                            Toast.makeText(LoginActivity.this,"Login succesfull",Toast.LENGTH_SHORT).show();

                            if (firebaseAuth.getCurrentUser() != null) {
                                finish();
                                startActivity(new Intent(getBaseContext(), MainActivity.class));
                            }

                        } else {
                            Toast.makeText(LoginActivity.this,"Could not Login , pleas try again !",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if (view == btn_LogInChat) {
            userLogin();
        }

        if (view == textViewSignUp) {
            finish();
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }
}
