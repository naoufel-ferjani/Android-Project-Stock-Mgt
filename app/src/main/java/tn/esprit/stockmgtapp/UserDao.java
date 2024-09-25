package tn.esprit.stockmgtapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM user_table WHERE email = :email AND password = :password")
    User login(String email, String password);

    @Query("SELECT * FROM user_table WHERE email = :email")
    User checkEmail(String email);

    @Query("SELECT * FROM user_table")
    List<User> getAllUsers();

}
