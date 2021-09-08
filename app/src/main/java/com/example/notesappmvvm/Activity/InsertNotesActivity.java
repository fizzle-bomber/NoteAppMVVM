package com.example.notesappmvvm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesappmvvm.Model.Notes;
import com.example.notesappmvvm.R;
import com.example.notesappmvvm.ViewModel.NotesViewModel;
import com.example.notesappmvvm.databinding.ActivityInsertNotesBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertNotesActivity extends AppCompatActivity {

    ActivityInsertNotesBinding binding;
    String title , subtitle , notes;
    NotesViewModel notesViewModel;
    FloatingActionButton button;
    ImageView img1 , img2 , img3;
    EditText tv1 , tv2 , tv3;
    String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_insert_notes);

        img1 = findViewById(R.id.greenPriority);
        img2 = findViewById(R.id.yellowPriority);
        img3 = findViewById(R.id.redPriority);
        button = findViewById(R.id.doneNotesBtn);
        tv1 = findViewById(R.id.notesTitle);
        tv2 = findViewById(R.id.notesSubtitle);
        tv3 = findViewById(R.id.notesData);

        notesViewModel = ViewModelProviders.of(this ).get(NotesViewModel.class);

        img1.setOnClickListener(v->{
            img1.setImageResource(R.drawable.ic_baseline_done_24);
            img2.setImageResource(0);
            img3.setImageResource(0);
            priority = "1";
        });
        img2.setOnClickListener(v->{
            img2.setImageResource(R.drawable.ic_baseline_done_24);
            img1.setImageResource(0);
            img3.setImageResource(0);
            priority = "2";
        });
       img3.setOnClickListener(v->{
            img3.setImageResource(R.drawable.ic_baseline_done_24);
            img1.setImageResource(0);
            img2.setImageResource(0);
            priority = "3";
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = tv1.getText().toString();
                subtitle = tv2.getText().toString();
                notes = tv3.getText().toString();

                CreateNotes(title , subtitle ,notes);
            }
        });


    }

    public void CreateNotes(String title , String subtitle , String notes)
    {
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate= DateFor.format(date);

       //CharSequence charSequence = DateFormat.format("MMMM d,YYYY",date.getTime());

        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notesSubtitle = subtitle;
        notes1.notes = notes;
        notes1.notesPriority = priority;
        notes1.notesDate = stringDate;
        notesViewModel.insertNotes(notes1);

        Toast.makeText(this , "Note created successfully", Toast.LENGTH_SHORT).show();

        finish();
    }
}