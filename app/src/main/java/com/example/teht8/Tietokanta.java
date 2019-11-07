package com.example.teht8;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MyEntity.class}, version = 1)
public abstract class Tietokanta extends RoomDatabase {
    public static final String NIMI = "TIETOKANTA";
    public abstract MyTableDao myTableDao();
}
