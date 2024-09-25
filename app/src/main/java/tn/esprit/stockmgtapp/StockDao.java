package tn.esprit.stockmgtapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StockDao {
    @Insert
    void insertStock(Stock stock);

    @Update
    void updateStock(Stock stock);

    @Delete
    void deleteStock(Stock stock);

    @Query("SELECT * FROM stock_table")
    List<Stock> getAllStock();

    @Insert
    void insert(Stock stock);

    @Update
    void update(Stock stock);

    // Delete a stock item
    @Delete
    void delete(Stock stock);
    @Query("SELECT * FROM stock_table WHERE id = :id")
    Stock getStockById(int id);

}
