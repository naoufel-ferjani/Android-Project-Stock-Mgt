package tn.esprit.stockmgtapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddProductActivity extends AppCompatActivity {

    private EditText etProductName, etQuantity;
    private Button btnAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        // Set up toolbar for back navigation
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize UI elements
        etProductName = findViewById(R.id.et_product_name);
        etQuantity = findViewById(R.id.et_quantity);
        btnAddProduct = findViewById(R.id.btn_add_product);

        // Set button click listener
        btnAddProduct.setOnClickListener(v -> {
            String productName = etProductName.getText().toString();
            String quantityStr = etQuantity.getText().toString();

            if (productName.isEmpty() || quantityStr.isEmpty()) {
                Toast.makeText(AddProductActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int quantity = Integer.parseInt(quantityStr);
            // Code to add product logic here
            Toast.makeText(AddProductActivity.this, "Product added successfully", Toast.LENGTH_SHORT).show();
            finish(); // Close activity
        });
    }

    // Handle back button click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Go back to the previous activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
