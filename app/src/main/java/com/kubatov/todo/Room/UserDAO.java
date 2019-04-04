package com.kubatov.todo.Room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.kubatov.todo.Activities.User;
import com.kubatov.todo.Task;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user ORDER BY NAME DESC")
    LiveData<List<User>> getNameSortDESC();

    @Query("SELECT * FROM user ORDER BY NAME ASC")
    LiveData<List<User>> getNameSortASC();

    @Query("SELECT * FROM user ORDER BY AGE DESC")
    LiveData<List<User>> getAgeSortDESC();

    @Query("SELECT * FROM user ORDER BY AGE ASC")
    LiveData<List<User>> getAgeSortASC();

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
