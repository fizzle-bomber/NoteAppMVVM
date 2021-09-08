package com.example.notesappmvvm.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notesappmvvm.Model.Notes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("Select * from Notes_Database")
    public LiveData<List<Notes>> getAllNotes();

    @Query("Select * from Notes_Database ORDER BY notes_priority DESC ")
    public LiveData<List<Notes>> highToLow();

    @Query("Select * from Notes_Database ORDER BY notes_priority ASC ")
    public LiveData<List<Notes>> lowToHigh();

    @Insert
    public void insertNotes(Notes notes);

    @Query("Delete from Notes_Database where id=:id")
    public void deleteNotes(int id);

    @Update
    public void updateNotes(Notes notes);
    
}
