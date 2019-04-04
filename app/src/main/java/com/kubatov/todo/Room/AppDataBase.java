package com.kubatov.todo.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.kubatov.todo.Activities.User;
import com.kubatov.todo.Task;

@Database(entities = {Task.class, User.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract TaskDAO taskDAO();
    public abstract UserDAO userDAO();

}
