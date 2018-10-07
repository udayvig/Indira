package com.example.android.indira;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUpActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    MaterialEditText emailEditText, passwordEditText;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth= FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        signUpButton = findViewById(R.id.signUpButton);

        if(firebaseUser != null){
            startActivity(new Intent(SignUpActivity.this ,HomeActivity.class));
        }

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!emailEditText.getText().toString().trim().isEmpty() && !passwordEditText.getText().toString().trim().isEmpty()){
                    final ProgressDialog progressDialog = ProgressDialog.show(SignUpActivity.this, "","Creating user...", true);
                    firebaseAuth.createUserWithEmailAndPassword(emailEditText.getText().toString().trim(), passwordEditText.getText().toString().trim()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            progressDialog.dismiss();
                            Snackbar.make(findViewById(android.R.id.content), "Account Created Successfully!", Snackbar.LENGTH_LONG)
                                    .show();
                            startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Snackbar.make(findViewById(android.R.id.content), "Account Creation Failed.", Snackbar.LENGTH_LONG)
                                    .show();
                        }
                    });
                }else if(!emailEditText.getText().toString().trim().isEmpty() && passwordEditText.getText().toString().trim().isEmpty()){
                    passwordEditText.setError("Required!");
                }else if(emailEditText.getText().toString().trim().isEmpty() && !passwordEditText.getText().toString().trim().isEmpty()){
                    emailEditText.setError("Required!");
                }else{
                    passwordEditText.setError("Required!");
                    emailEditText.setError("Required!");
                }
            }
        });



    }
}
