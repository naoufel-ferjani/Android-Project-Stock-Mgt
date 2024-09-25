package tn.esprit.stockmgtapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private AppDatabase db;
    private ListView listView;
    private UserAdapter userAdapter;
    private Button btnAddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        // Initialize the database
        db = AppDatabase.getInstance(this);

        // Initialize the ListView and Add User button
        listView = findViewById(R.id.user_list_view);
        btnAddUser = findViewById(R.id.btn_add_user);

        // Load the user list
        loadUserList();

        // Set up Add User button click listener
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the AddUserActivity (You will need to create this activity)
                Intent intent = new Intent(UserListActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload user list when returning from the Add User screen
        loadUserList();
    }

    private void loadUserList() {
        // Get all users from the database
        List<User> userList = db.userDao().getAllUsers();

        // Initialize the adapter and pass the user list to it
        userAdapter = new UserAdapter(this, userList);
        listView.setAdapter(userAdapter);
    }
}
