package com.skuy.deathnote.model;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "gambar")
    private String gambar;

    @ColumnInfo(name = "judul")
    private String judul;

    @ColumnInfo(name = "penulis")
    private String penulis;

    @ColumnInfo(name = "cerita")
    private String cerita;

    public Note() {

    }

    public Note(int id, String gambar, String judul, String penulis, String cerita) {
        this.id = id;
        this.gambar = gambar;
        this.judul = judul;
        this.penulis = penulis;
        this.cerita = cerita;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getCerita() {
        return cerita;
    }

    public void setCerita(String cerita) {
        this.cerita = cerita;
    }
}
