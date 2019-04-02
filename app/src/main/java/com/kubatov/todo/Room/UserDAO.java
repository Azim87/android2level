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

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
