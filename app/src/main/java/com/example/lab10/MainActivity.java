package com.example.lab10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    String username;
    String email;
    String password;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText usernameTxt = findViewById(R.id.editTextUserName);
        EditText emailTxt = findViewById(R.id.editTextEmail);
        EditText passwordTxt = findViewById(R.id.editTextPassword);
        Button signup = (Button) findViewById(R.id.button);
        
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernameTxt.getText().toString().isEmpty() ||
                        emailTxt.getText().toString().isEmpty() ||
                        passwordTxt.getText().toString().isEmpty() )
                {
                    Toast.makeText(MainActivity.this, "Empty fields are required to complete", Toast.LENGTH_LONG).show();
                }
                else{
                    username = usernameTxt.getText().toString();
                    email= emailTxt.getText().toString();
                    password= passwordTxt.getText().toString();
                    Toast.makeText(MainActivity.this, "add user", Toast.LENGTH_SHORT).show();
                    addUser(email,password);
                }
            }
        });
    }
    
    public void addUser(String email1, String pass){
        mAuth.createUserWithEmailAndPassword(email1,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    currentUser= mAuth.getCurrentUser();
                    String userEmail = currentUser.getEmail();
                    Toast.makeText(MainActivity.this, "Successful registration. "+ userEmail + " is now added to Twitter", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "User Already Registered ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}