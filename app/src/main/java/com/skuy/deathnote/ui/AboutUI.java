package com.skuy.deathnote.ui;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AboutUI extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ui_about, container, false);

        return view;
    }
}
