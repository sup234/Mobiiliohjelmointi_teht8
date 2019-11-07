package com.example.teht8;

import android.widget.ImageView;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MyEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String file;
    public String owner;
    public String license;
    /*public int width;
    public int height;
    public String filter;
    public String tags;
    public String tagMode;
    public String file;*/
}
