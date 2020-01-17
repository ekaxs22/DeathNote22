package com.skuy.deathnote.note;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.skuy.deathnote.R;
import com.skuy.deathnote.model.Note;
import com.skuy.deathnote.room.AppDatabase;
import com.skuy.deathnote.room.NoteRoom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TambahNoteActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE = 100;
    ImageView ivGambar;
    Uri imageUri;

    EditText edtJudul, edtPenulis, edtCerita;
    Button btnTambah;
    Button btnUpdate;
    Button btnHapus;

    NoteRoom noteRoom;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_note);

        getSupportActionBar().setTitle("Tambah Cerita");
        edtJudul = findViewById(R.id.note_tambah_judul);
        edtPenulis = findViewById(R.id.note_tambah_penulis);
        edtCerita = findViewById(R.id.note_tambah_cerita);

        ivGambar = findViewById(R.id.note_tambah_gambar);
        ivGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE);
            }
        });

        btnTambah = findViewById(R.id.note_tambah);
        btnUpdate = findViewById(R.id.note_update);
        btnHapus = findViewById(R.id.note_hapus);
        btnTambah.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnHapus.setOnClickListener(this);

//        Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();

        noteRoom = AppDatabase.db(this).noteRoom();

        int id = getIntent().getIntExtra("id", -1);
        note = noteRoom.select(id);

        if (id != -1) {
            if (imageUri == null) {
                imageUri = Uri.parse(note.getGambar());
                ivGambar.setImageURI(imageUri);
            }
            edtJudul.setText(note.getJudul());
            edtPenulis.setText(note.getPenulis());
            edtCerita.setText(note.getCerita());

            btnUpdate.setVisibility(View.VISIBLE);
            btnHapus.setVisibility(View.VISIBLE);
        } else {
            btnTambah.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnTambah)) {
            Note noteBaru = new Note();
            noteBaru.setJudul(edtJudul.getText().toString());
            noteBaru.setPenulis(edtPenulis.getText().toString());
            noteBaru.setCerita(edtCerita.getText().toString());
            noteBaru.setGambar(imageUri.toString());

            if (edtJudul.length()==0 || edtPenulis.length()==0 || edtCerita.length()==0) {
                Toast.makeText(getApplicationContext(), "Kolom tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                noteRoom.insert(noteBaru);
                Toast.makeText(getApplicationContext(), "Berhasil Menambahkan Cerita", Toast.LENGTH_SHORT).show();
            }

//            noteRoom.insert(noteBaru);
//            Toast.makeText(getApplicationContext(), "Berhasil Menambahkan Cerita", Toast.LENGTH_SHORT).show();

        } else if (v.equals(btnUpdate)) {
            note.setJudul(edtJudul.getText().toString());
            note.setPenulis(edtPenulis.getText().toString());
            note.setCerita(edtCerita.getText().toString());
            note.setGambar(imageUri.toString());
            noteRoom.update(note);

            if (edtJudul.length()==0 || edtPenulis.length()==0 || edtCerita.length()==0) {
                Toast.makeText(getApplicationContext(), "Kolom tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(getApplicationContext(), "Berhasil Mengupdate Cerita", Toast.LENGTH_SHORT).show();
            }

//            Toast.makeText(getApplicationContext(), "Berhasil Mengupdate Cerita", Toast.LENGTH_SHORT).show();

        } else if (v.equals(btnHapus)) {
            noteRoom.delete(note);
            Toast.makeText(getApplicationContext(), "Berhasil Menghapus Cerita", Toast.LENGTH_SHORT).show();

        }
        setResult(Activity.RESULT_OK, new Intent());
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            ivGambar.setImageURI(imageUri);
        }
    }
}
