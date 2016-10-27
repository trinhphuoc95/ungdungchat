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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_RegisterUser;
    private Button btn_Cancel;
    private EditText txtRegisterUserName;
    private EditText txtRegisterEmail;
    private EditText txtRegisterPassword;
    private TextView textViewSignin;


    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        btn_RegisterUser = (Button) findViewById(R.id.btn_RegisterUser);
        btn_Cancel = (Button) findViewById(R.id.btn_Cancel);
        txtRegisterUserName = (EditText) findViewById(R.id.txtRegisterUserName);
        txtRegisterEmail = (EditText) findViewById(R.id.txtRegisterEmail);
        txtRegisterPassword = (EditText) findViewById(R.id.txtRegisterPassword);

        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        btn_RegisterUser.setOnClickListener(this);
        btn_Cancel.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);

    }

    private void registerUser() {
        String userName = txtRegisterUserName.getText().toString().trim();
        String email = txtRegisterEmail.getText().toString().trim();
        String password = txtRegisterPassword.getText().toString().trim();

        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Please enter UserName !", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter Email !", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter Password !", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering Please Wait... ");
        progressDialog.show();

        //

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           Toast.makeText(RegisterActivity.this,"Register Successfully",Toast.LENGTH_SHORT).show();
                           finish();
                           startActivity(new Intent(getApplicationContext(), MainActivity.class));

                       }else{
                           Toast.makeText(RegisterActivity.this,"Could not Register , pleas try again !",Toast.LENGTH_SHORT).show();
                       }
                    }
                });
    }

    private void Canel(){
        txtRegisterPassword.setText("");
        txtRegisterEmail.setText("");
        txtRegisterUserName.setText("");
    }

    @Override
    public void onClick(View view) {

        if (view == btn_RegisterUser) {
            registerUser();
        }

        if (view == btn_Cancel) {
            Canel();
        }

        if (view == textViewSignin) {
          startActivity(new Intent(this,LoginActivity.class));
        }
    }
}
