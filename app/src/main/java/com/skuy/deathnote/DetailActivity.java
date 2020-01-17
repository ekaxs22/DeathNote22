package com.skuy.deathnote;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.skuy.deathnote.model.Note;
import com.skuy.deathnote.note.TambahNoteActivity;
import com.skuy.deathnote.room.AppDatabase;
import com.skuy.deathnote.room.NoteRoom;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    ImageView ivCover;
    TextView tvJudul, tvPenulis, tvCerita;
    Button btUpdate;
    Note note;
    NoteRoom noteRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);

        noteRoom = AppDatabase.db(getApplicationContext()).noteRoom();
        note = noteRoom.select(id);

        ivCover = findViewById(R.id.detail_cover);
        tvJudul = findViewById(R.id.detail_judul);
        tvPenulis = findViewById(R.id.detail_penulis);
        tvCerita = findViewById(R.id.detail_cerita);
        btUpdate = findViewById(R.id.detail_update);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), TambahNoteActivity.class);
                intent.putExtra("id", note.getId());
                startActivityForResult(intent, 30);
            }
        });

        tvCerita.setMovementMethod(new ScrollingMovementMethod());

        if (note != null) {
            setTitle("Detail Cerita");
//            setTitle(note.getJudul());
            ivCover.setImageURI(Uri.parse(note.getGambar()));
            tvJudul.setText(note.getJudul());
            tvPenulis.setText(note.getPenulis());
            tvCerita.setText(note.getCerita());
        }
    }
}
