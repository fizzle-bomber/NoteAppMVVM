package com.example.notesappmvvm.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesappmvvm.Model.Notes;
import com.example.notesappmvvm.R;
import com.example.notesappmvvm.ViewModel.NotesViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {

    EditText tv1 , tv2 , tv3;
    String priority = "1";
    int sid;
    ImageView img1 , img2 , img3;
    FloatingActionButton btnup;
    String stitle , ssubtitle, spriority , snotes;
    NotesViewModel notesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_notes);

        sid = getIntent().getIntExtra("id",0);
        stitle = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("subtitle");
        spriority = getIntent().getStringExtra("priority");
        snotes = getIntent().getStringExtra("notesData");

        notesViewModel = ViewModelProviders.of(this ).get(NotesViewModel.class);

        img1 = findViewById(R.id.greenPriority);
        img2 = findViewById(R.id.yellowPriority);
        img3 = findViewById(R.id.redPriority);
        tv1 = findViewById(R.id.upTitle);
        tv2 = findViewById(R.id.upSubtitle);
        tv3 = findViewById(R.id.upNotes);
        btnup = findViewById(R.id.updateNotesBtn);

        if("1".equals(spriority))
        {
            img1.setImageResource(R.drawable.ic_baseline_done_24);
            img2.setImageResource(0);
            img3.setImageResource(0);
        }
        else if("2".equals(spriority)){
            img2.setImageResource(R.drawable.ic_baseline_done_24);
            img3.setImageResource(0);
            img1.setImageResource(0);
        }
        else if("3".equals(spriority)){
            img3.setImageResource(R.drawable.ic_baseline_done_24);
            img2.setImageResource(0);
            img1.setImageResource(0);
        }
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

        tv1.setText(stitle);
        tv2.setText(ssubtitle);
        tv3.setText(snotes);

        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = tv1.getText().toString();
                String subtitle = tv2.getText().toString();
                String notes = tv3.getText().toString();
                
                UpdateNotes(title , subtitle , notes);
            }
        });
    }

    private void UpdateNotes(String title, String subtitle, String notes) {
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate= DateFor.format(date);

        Notes updateNotes = new Notes();
        updateNotes.id = sid;
        updateNotes.notesTitle = title;
        updateNotes.notesSubtitle = subtitle;
        updateNotes.notes = notes;
        updateNotes.notesPriority = priority;
        updateNotes.notesDate = stringDate;
        notesViewModel.updateNotes(updateNotes);

        Toast.makeText(this , "Notes Updated successfully" , Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.delete)
        {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(UpdateNotesActivity.this,R.style.BottomSheetStyle);
            View view = getLayoutInflater().from(UpdateNotesActivity.this)
                    .inflate(R.layout.delete_bottom_sheet,(LinearLayout)findViewById(R.id.bottonsheet));
            bottomSheetDialog.setContentView(view);
            bottomSheetDialog.show();


            TextView yes , no;
            yes = view.findViewById(R.id.delete_yes);
            no  = view.findViewById(R.id.delete_no);

            yes.setOnClickListener(v->{
                notesViewModel.deleteNotes(sid);
                finish();
            });
            no.setOnClickListener(v->{
                bottomSheetDialog.dismiss();
            });
        }
        return true;
    }
}