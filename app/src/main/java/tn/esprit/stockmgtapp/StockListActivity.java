package tn.esprit.stockmgtapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class StockListActivity extends AppCompatActivity {

    private AppDatabase db;
    private ListView listView;
    private StockAdapter stockAdapter;
    private Button btnAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_list);

        // Initialize the database
        db = AppDatabase.getInstance(this);

        // Initialize the ListView and Add Product button
        listView = findViewById(R.id.stock_list_view);
        btnAddProduct = findViewById(R.id.btn_add_product);

        // Load the stock items
        loadStockList();

        // Set up Add Product button click listener
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the AddProductActivity
                Intent intent = new Intent(StockListActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload stock list when returning from the Add Product screen
        loadStockList();
    }

    private void loadStockList() {
        // Get all stock items from the database
        List<Stock> stockList = db.stockDao().getAllStock();

        // Initialize the adapter and pass the database instance to it
        stockAdapter = new StockAdapter(this, stockList, db);
        listView.setAdapter(stockAdapter);
    }
}
