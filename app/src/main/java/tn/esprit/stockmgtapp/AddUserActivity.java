package tn.esprit.stockmgtapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddUserActivity extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword;
    private Button btnSaveUser;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        // Initialize database
        db = AppDatabase.getInstance(this);

        // Initialize UI components
        etUsername = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnSaveUser = findViewById(R.id.btn_save_user);

        // Set button click listener to save the user
        btnSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
            }
        });
    }

    private void saveUser() {
        String username = etUsername.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        // Validate fields
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(AddUserActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new user
        User newUser = new User(username, email, password);

        // Insert the user into the database
        db.userDao().insertUser(newUser);

        // Show success message
        Toast.makeText(AddUserActivity.this, "User added successfully", Toast.LENGTH_SHORT).show();

        // Finish the activity and return to the user list
        finish();
    }
}
