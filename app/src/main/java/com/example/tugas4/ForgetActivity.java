package com.example.tugas4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetActivity extends AppCompatActivity {
    EditText emailAd;
    Button btnfind;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        emailAd = findViewById(R.id.email);
        btnfind = findViewById(R.id.button);
        mFirebaseAuth = FirebaseAuth.getInstance();

        btnfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailAd.getText().toString();
                boolean valid = true;
                if (email.isEmpty()) {
                    emailAd.setText("Masukkan email");
                    emailAd.requestFocus();
                    Toast.makeText(ForgetActivity.this, "Email kosong", Toast.LENGTH_SHORT).show();
                    valid = false;
                }
                if(valid){
                    mFirebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(ForgetActivity.this, "Password telah dikirim", Toast.LENGTH_SHORT).show();
                            Intent g = new Intent(ForgetActivity.this, LoginActivity.class);
                            startActivity(g);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ForgetActivity.this, "Email tidak ditemukan", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}