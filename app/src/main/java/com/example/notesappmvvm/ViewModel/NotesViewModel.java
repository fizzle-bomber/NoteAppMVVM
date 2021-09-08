package com.example.notesappmvvm.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notesappmvvm.Model.Notes;
import com.example.notesappmvvm.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository notesRepository;
    public LiveData<List<Notes>> getallNotes;
    public LiveData<List<Notes>> hightolow;
    public LiveData<List<Notes>> lowtohigh;


    public NotesViewModel(@NonNull Application application)
    {
        super(application);
        notesRepository = new NotesRepository(application);
        getallNotes = notesRepository.getAllNotes;
        hightolow = notesRepository.hightolow;
        lowtohigh = notesRepository.lowtohigh;
    }

    public void insertNotes(Notes notes)
    {
        notesRepository.insertNotes(notes);
    }

    public void deleteNotes(int id)
    {
        notesRepository.deleteNotes(id);
    }

    public void updateNotes(Notes notes)
    {
        notesRepository.updateNotes(notes);
    }

}
