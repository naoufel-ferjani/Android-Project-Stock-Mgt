package tn.esprit.stockmgtapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class SignupActivity extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword;
    private Button btnSignup, btnLogin;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize UI elements
        etUsername = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnSignup = findViewById(R.id.btn_signup);
        btnLogin = findViewById(R.id.btn_login);

        // Initialize Room Database
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user_database").allowMainThreadQueries().build();

        // Signup button click listener
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                // Check if fields are empty
                if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if email already exists
                User existingUser = db.userDao().checkEmail(email);
                if (existingUser != null) {
                    Toast.makeText(SignupActivity.this, "Email already registered", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Insert new user into the database
                User newUser = new User(username, email, password);
                db.userDao().insertUser(newUser);

                Toast.makeText(SignupActivity.this, "Signup successful", Toast.LENGTH_SHORT).show();

                // Redirect to the login activity
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the signup activity
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }




}
