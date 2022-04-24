package com.skz.movieguide.roomdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MovieEntity.class},version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDAO movieDAO();
}
