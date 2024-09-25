package tn.esprit.stockmgtapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StockAdapter extends BaseAdapter {

    private Context context;
    private List<Stock> stockList;
    private AppDatabase db; // Add the database instance

    public StockAdapter(Context context, List<Stock> stockList, AppDatabase db) { // Modify constructor
        this.context = context;
        this.stockList = stockList;
        this.db = db;  // Initialize the database
    }

    @Override
    public int getCount() {
        return stockList.size();
    }

    @Override
    public Object getItem(int position) {
        return stockList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return stockList.get(position).getId(); // Assuming Stock has a getId method
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.stock_item, null);
        }

        TextView productName = convertView.findViewById(R.id.product_name);
        TextView quantityTextView = convertView.findViewById(R.id.quantity);
        Button btnEdit = convertView.findViewById(R.id.btn_edit);
        Button btnDelete = convertView.findViewById(R.id.btn_delete);
        Button btnIncreaseQuantity = convertView.findViewById(R.id.btn_increase_quantity);
        Button btnDecreaseQuantity = convertView.findViewById(R.id.btn_decrease_quantity);

        final Stock stock = stockList.get(position);
        productName.setText(stock.getProductName());
        quantityTextView.setText(String.valueOf(stock.getQuantity()));

        // Handle increasing quantity
        btnIncreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity = stock.getQuantity() + 1;
                stock.setQuantity(newQuantity);
                db.stockDao().update(stock);  // Update in the database
                quantityTextView.setText(String.valueOf(newQuantity));  // Update UI
            }
        });

        // Handle decreasing quantity
        btnDecreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity = stock.getQuantity() - 1;
                if (newQuantity >= 0) {  // Ensure quantity doesn't go below zero
                    stock.setQuantity(newQuantity);
                    db.stockDao().update(stock);  // Update in the database
                    quantityTextView.setText(String.valueOf(newQuantity));  // Update UI
                }
            }
        });

        // Handle Edit button click
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditProductActivity.class);
                intent.putExtra("productId", stock.getId());
                context.startActivity(intent);
            }
        });

        // Handle Delete button click
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.stockDao().delete(stock);  // Delete from database
                stockList.remove(position);   // Remove from list
                notifyDataSetChanged();       // Refresh ListView
                Toast.makeText(context, "Product deleted", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

}
