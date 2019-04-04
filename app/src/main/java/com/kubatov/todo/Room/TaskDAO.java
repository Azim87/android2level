package com.kubatov.todo.Room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.kubatov.todo.Task;

import java.util.List;

import static android.icu.text.MessagePattern.ArgType.SELECT;

@Dao
public interface TaskDAO {

    @Query("SELECT*FROM task")
    LiveData<List<Task>>getAll();

    @Query("SELECT * FROM task ORDER BY TITLE DESC")
    LiveData<List<Task>> sortByIdDesc();

    @Query("SELECT * FROM task ORDER BY TITLE ASC")
    LiveData<List<Task>> sortByIdAsc();

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);


}
