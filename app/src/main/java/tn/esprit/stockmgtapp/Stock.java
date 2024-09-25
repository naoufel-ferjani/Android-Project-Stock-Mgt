package tn.esprit.stockmgtapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "stock_table")
public class Stock {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "product_name")
    public String productName;

    @ColumnInfo(name = "quantity")
    public int quantity;

    // Constructor
    public Stock(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
