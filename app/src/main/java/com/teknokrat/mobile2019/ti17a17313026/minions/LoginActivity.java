package com.teknokrat.mobile2019.ti17a17313026.minions;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout inputUsername;
    private TextInputLayout inputPassword;
    private String username;
    private String password;
    private FirebaseAuth auth;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseApp.initializeApp(getApplicationContext());
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser!=null){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        inputUsername = findViewById(R.id.login_input_username);
        inputPassword = findViewById(R.id.login_input_password);
        Button btnLogin = findViewById(R.id.login_btn_login);
        Button btnRegister = findViewById(R.id.login_btn_register);
        Objects.requireNonNull(inputUsername.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateUsername();
            }
        });
        Objects.requireNonNull(inputPassword.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                validatePassword();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUsername();
                validatePassword();
                setLogin(username, password);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUsername();
                validatePassword();
                if(username.length()>0&&password.length()>0) {
                    auth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Register Success!\nPlease Login", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginActivity.this, "Check Your Data Again!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void validateUsername() {
        username = Objects.requireNonNull(inputUsername.getEditText()).getText().toString().trim();
        if (username.isEmpty()) {
            inputUsername.setError("Email tidak boleh kosong");
        } else if (username.length() < 5) {
            inputUsername.setError("Email minimal memiliki 5 karakter");
        } else {
            inputUsername.setError(null);
        }
    }

    private void validatePassword() {
        password = Objects.requireNonNull(inputPassword.getEditText()).getText().toString().trim();
        if (password.isEmpty()) {
            inputPassword.setError("Password tidak boleh kosong");
        } else if (password.length() < 5) {
            inputPassword.setError("Password minimal memiliki 5 karakter");
        } else {
            inputPassword.setError(null);
        }
    }

    public void setLogin(String username, String password) {
        if(username.length()>0&&password.length()>0) {
            auth.signInWithEmailAndPassword(username, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        finish();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        inputUsername.setError(getResources().getString(R.string.wrong_email));
                        inputPassword.setError(getResources().getString(R.string.wrong_password));
                    }
                }
            });
        }
    }
}
