package com.example.notesappmvvm.Adapter;

import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesappmvvm.Activity.UpdateNotesActivity;
import com.example.notesappmvvm.MainActivity;
import com.example.notesappmvvm.Model.Notes;
import com.example.notesappmvvm.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewholder> {

    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> allNotesitem;
    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes  = notes;
        allNotesitem = new ArrayList<>(notes);
    }

    public void searchNotes(List<Notes> filteredName){

        this.notes = filteredName;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public notesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new notesViewholder(LayoutInflater.from(mainActivity).inflate(R.layout.items_notes , parent,false));
    }

    @Override
    public void onBindViewHolder(NotesAdapter.notesViewholder holder, int position) {
        Notes note = notes.get(position);

        if(note.notesPriority.equals("1")){
            holder.notesPriority.setBackgroundResource(R.drawable.green_dot);
        }
        else if(note.notesPriority.equals("2"))
        {
            holder.notesPriority.setBackgroundResource(R.drawable.yellow_dot);
        }
        else
        {
            holder.notesPriority.setBackgroundResource(R.drawable.red_dot);
        }
        holder.title.setText(note.notesTitle);
        holder.subtitle.setText(note.notesSubtitle);
        holder.notesDate.setText(note.notesDate);

        holder.itemView.setOnClickListener(v->{

            Intent intent = new Intent(holder.itemView.getContext() , UpdateNotesActivity.class);
            intent.putExtra("id" , note.id);
            intent.putExtra("title" , note.notesTitle);
            intent.putExtra("subtitle" , note.notesSubtitle);
            intent.putExtra("priority" , note.notesPriority);
            intent.putExtra("notesData" , note.notes);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class notesViewholder extends RecyclerView.ViewHolder {

        TextView title , subtitle , notesDate ;
        View notesPriority;
        public notesViewholder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.notesTitle);
            subtitle = itemView.findViewById(R.id.notesSubtitle);
            notesDate = itemView.findViewById(R.id.notesDate );
            notesPriority = itemView.findViewById(R.id.notesPriority);


        }
    }
}
