package com.example.teht8;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyTableDao {

    @Query("SELECT * FROM myentity ORDER BY id desc")
    List<MyEntity> getAllInDescendingOrder();

    @Query("SELECT file FROM myentity")
    List<MyEntity> getUrls();

    @Query("SELECT license FROM myentity")
    List<MyEntity> getLicenses();

    @Query("SELECT owner FROM myentity")
    List<MyEntity> getOwners();

    @Insert
    void InsertMyEntity(MyEntity myentity);

    @Delete
    void DeleteMyEntity(MyEntity myentity);
}
