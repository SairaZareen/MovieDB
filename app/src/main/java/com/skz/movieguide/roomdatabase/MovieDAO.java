package com.skz.movieguide.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addFavorite(MovieEntity movieEntity);

    @Query("select * from favorites")
    public LiveData<List<MovieEntity>> getFavoriteMovies();

    @Query("SELECT EXISTS (SELECT 1 FROM favorites WHERE movie_id=:id)")
    public int isFavorite(int id);

    @Delete
    public void deleteFavorite(MovieEntity movieEntity);
}
