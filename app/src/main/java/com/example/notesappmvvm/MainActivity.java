package com.example.notesappmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.notesappmvvm.Activity.InsertNotesActivity;
import com.example.notesappmvvm.Adapter.NotesAdapter;
import com.example.notesappmvvm.Model.Notes;
import com.example.notesappmvvm.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    FloatingActionButton floatingActionButton;
    NotesViewModel notesViewModel;
    RecyclerView notesRecycler;
    NotesAdapter notesAdapter;

    TextView tv1 , tv2 ,tv3;
    List<Notes> filternotesalllist;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.newNotesBtn);

        notesViewModel = ViewModelProviders.of(this ).get(NotesViewModel.class);

        notesRecycler = findViewById(R.id.notesRecycler);

        tv1 = findViewById(R.id.nofilter);
        tv2 = findViewById(R.id.hightolow);
        tv3 = findViewById(R.id.lowtohigh);

        tv1.setBackgroundResource(R.drawable.filter_sel_shape);

        tv1.setOnClickListener(v->{
            loadData(0);
            tv2.setBackgroundResource(R.drawable.filter_shape);
            tv3.setBackgroundResource(R.drawable.filter_shape);
            tv1.setBackgroundResource(R.drawable.filter_sel_shape);

        });

        tv2.setOnClickListener(v->{
            loadData(1);
            tv1.setBackgroundResource(R.drawable.filter_shape);
            tv3.setBackgroundResource(R.drawable.filter_shape);
            tv2.setBackgroundResource(R.drawable.filter_sel_shape);
        });

        tv3.setOnClickListener(v->{
            loadData(2);
            tv2.setBackgroundResource(R.drawable.filter_shape);
            tv1.setBackgroundResource(R.drawable.filter_shape);
            tv3.setBackgroundResource(R.drawable.filter_sel_shape);
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , InsertNotesActivity.class));
            }
        });
            notesViewModel.getallNotes.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternotesalllist = notes;
                }
            });

    }

    private void loadData(int i) {
            if(i == 0){
                notesViewModel.getallNotes.observe(this, new Observer<List<Notes>>() {
                    @Override
                    public void onChanged(List<Notes> notes) {
                        setAdapter(notes);
                        filternotesalllist = notes;
                    }
                });
            }
            else if(i==1)
            {
                notesViewModel.hightolow.observe(this, new Observer<List<Notes>>() {
                    @Override
                    public void onChanged(List<Notes> notes) {
                        setAdapter(notes);
                        filternotesalllist = notes;
                    }
                });
            }
            else {
                notesViewModel.lowtohigh.observe(this, new Observer<List<Notes>>() {
                    @Override
                    public void onChanged(List<Notes> notes) {
                        setAdapter(notes);
                        filternotesalllist = notes;
                    }
                });
            }
    }

    public void setAdapter(List<Notes> notes){
        notesRecycler.setLayoutManager(new GridLayoutManager(this , 2));
        notesAdapter = new NotesAdapter(MainActivity.this , notes);
        notesRecycler.setAdapter(notesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.search_notes,menu);
            MenuItem menuItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView =(SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Notes here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Notesfilter(newText);
                return false;
            }
        });
        return true;
    }

    private void Notesfilter(String newText) {
        ArrayList<Notes> filterName = new ArrayList<>();

        for(Notes notes:this.filternotesalllist){
            if(notes.notesTitle.contains(newText) || notes.notesSubtitle.contains(newText)){
                filterName.add(notes);
            }
        }
        this.notesAdapter.searchNotes(filterName);
    }
}