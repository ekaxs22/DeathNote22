package com.skuy.deathnote.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.skuy.deathnote.DetailActivity;
import com.skuy.deathnote.R;
import com.skuy.deathnote.adapter.NoteAdapter;
import com.skuy.deathnote.model.Note;
import com.skuy.deathnote.note.TambahNoteActivity;
import com.skuy.deathnote.room.AppDatabase;
import com.skuy.deathnote.room.NoteRoom;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeUI extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener{
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private List<Note> list = new ArrayList<>();
    private NoteRoom noteRoom;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteRoom = AppDatabase.db(getContext()).noteRoom();
        list = noteRoom.selectAll();
        noteAdapter = new NoteAdapter (getContext(), list, this);
    }

    @SuppressLint("WrongConstant")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ui_home, container, false);

        recyclerView = view.findViewById(R.id.recycler_note);
        recyclerView.setAdapter(noteAdapter);

//        GridLayoutManager gridm = new GridLayoutManager(getContext(), 2);
//        recyclerView.setLayoutManager(gridm);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(manager);

        FloatingActionButton fab = view.findViewById(R.id.fab_note);
        fab.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 30 && resultCode == Activity.RESULT_OK) {
            list.clear();
            list.addAll(noteRoom.selectAll());
            noteAdapter.notifyDataSetChanged();
        }
        if (requestCode == 50 && resultCode == Activity.RESULT_OK) {
            list.clear();
            list.addAll(noteRoom.selectAll());
            noteAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), TambahNoteActivity.class);
        startActivityForResult(intent, 30);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Note note = list.get(position);
        Toast.makeText(getContext(), note.getJudul(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("id", note.getId());
        startActivityForResult(intent, 50);

    }

    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        list.addAll(noteRoom.selectAll());
        noteAdapter.notifyDataSetChanged();
    }
}
