package com.example.dinhtrinhphuoc.friendlychat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private Toolbar mSignUpToolbar;

    private EditText mNameField;
    private EditText mEmailField;
    private EditText mPasswordField;

    private Button mRegisterBtn;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("message");

        mProgress = new ProgressDialog(this);

        mNameField = (EditText) findViewById(R.id.NameField);
        mEmailField = (EditText) findViewById(R.id.EmailField);
        mPasswordField = (EditText) findViewById(R.id.PasswordField);

        mRegisterBtn = (Button) findViewById(R.id.RegisterBtn);

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegister();
            }
        });

        //Toolbar
        mSignUpToolbar = (Toolbar) findViewById(R.id.signUpToolbar);
        setSupportActionBar(mSignUpToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void startRegister() {
        final String name = mNameField.getText().toString().trim();
        final String email = mEmailField.getText().toString().trim();
        final String password = mPasswordField.getText().toString().trim();

        mProgress.setMessage("Signing Up ...");
        mProgress.show();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    String user_id = mAuth.getCurrentUser().getUid();
                    DatabaseReference current_user_db = mDatabase.child(user_id);
                    UserMessage userMessage = new UserMessage();
                    userMessage.setName(name);
                    userMessage.setPhotoUrl("");
                    userMessage.setText(email);
                    current_user_db.setValue(userMessage);

                    mProgress.dismiss();

                    Intent mainIntent = new Intent(RegisterActivity.this, ListUserActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mainIntent);

                }
            });
        }
    }

}
