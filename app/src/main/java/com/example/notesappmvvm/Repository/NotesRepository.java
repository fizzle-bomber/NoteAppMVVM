package com.example.notesappmvvm.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notesappmvvm.Dao.NotesDao;
import com.example.notesappmvvm.Database.NotesDatabase;
import com.example.notesappmvvm.Model.Notes;

import java.util.List;

public class NotesRepository {

    public NotesDao notesDao;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> hightolow;
    public LiveData<List<Notes>> lowtohigh;

    public NotesRepository(Application application)
    {
        NotesDatabase notesDatabase = NotesDatabase.getDatabaseInstance(application);
        notesDao = notesDatabase.notesDao();
        getAllNotes = notesDao.getAllNotes();
        hightolow = notesDao.highToLow();
        lowtohigh = notesDao.lowToHigh();
    }

    public void insertNotes(Notes notes)
    {
        notesDao.insertNotes(notes);
    }

    public void deleteNotes(int id)
    {
        notesDao.deleteNotes(id);
    }

    public void updateNotes(Notes notes)
    {
        notesDao.updateNotes(notes);
    }
}
