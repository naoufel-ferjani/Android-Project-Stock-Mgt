package tn.esprit.stockmgtapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditProductActivity extends AppCompatActivity {

    private EditText etProductName, etQuantity;
    private Button btnSave;
    private AppDatabase db;
    private Stock stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        etProductName = findViewById(R.id.et_product_name);
        etQuantity = findViewById(R.id.et_quantity);
        btnSave = findViewById(R.id.btn_save);

        db = AppDatabase.getInstance(getApplicationContext());

        // Retrieve the product ID passed from the intent
        int productId = getIntent().getIntExtra("productId", -1);
        stock = db.stockDao().getStockById(productId);

        // Populate fields with the current product data
        if (stock != null) {
            etProductName.setText(stock.getProductName());
            etQuantity.setText(String.valueOf(stock.getQuantity()));
        }

        // Save button click listener
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = etProductName.getText().toString();
                String quantityStr = etQuantity.getText().toString();

                if (productName.isEmpty() || quantityStr.isEmpty()) {
                    Toast.makeText(EditProductActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                int quantity = Integer.parseInt(quantityStr);

                // Update stock and save to the database
                stock.setProductName(productName);
                stock.setQuantity(quantity);
                db.stockDao().update(stock);

                Toast.makeText(EditProductActivity.this, "Product updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
