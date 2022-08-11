package com.example.uas_akb_if3_10119124;

/*NIM  : 10119124
 * Nama : Primarazaq Noorshalih Putra Hilmana
 * Kelas : IF-3*/

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassActivity extends AppCompatActivity {
    private EditText inputEmail;
    private Button btnSendResetEmail;
    private TextView btnLogin;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        inputEmail = (EditText) findViewById(R.id.email);
        btnSendResetEmail = (Button) findViewById(R.id.btnResetEmail);
        btnLogin = (TextView)findViewById(R.id.btnLogin);

        auth = FirebaseAuth.getInstance();

        btnSendResetEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();

                if (TextUtils.isEmpty(email)){
                    inputEmail.setError("Fill up ur email!");
                }else {
                    auth.sendPasswordResetEmail(email).addOnCompleteListener(ForgotPassActivity.this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ForgotPassActivity.this, "Your password succesfully reseted", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(ForgotPassActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPassActivity.this, LoginActivity.class));
            }
        });
    }
}