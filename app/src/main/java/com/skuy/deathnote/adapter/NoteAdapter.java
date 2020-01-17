package com.skuy.deathnote.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skuy.deathnote.R;
import com.skuy.deathnote.model.Note;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    private Context context;
    private List<Note> note;
    private AdapterView.OnItemClickListener listener;

    public NoteAdapter(Context context, List<Note> note,
                       AdapterView.OnItemClickListener listener) {
        this.context = context;
        this.note = note;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_note, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Note note = this.note.get(i);
        viewHolder.ivCover.setImageURI(Uri.parse(note.getGambar()));
        viewHolder.tvJudul.setText(note.getJudul());
        viewHolder.tvPenulis.setText(note.getPenulis());
        Glide.with(viewHolder.itemView)
                .load(note.getGambar())
                .into(viewHolder.ivCover);

//        viewHolder.tvKategori.setText(buku.getKategori());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(null, viewHolder.itemView, i, i);
            }
        });
//        viewHolder.itemView.setOnLongClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onItemLongClick(null, viewHolder.itemView, i, i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return note.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        public TextView tvJudul;
        public TextView tvPenulis;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.note_cover);
            tvJudul = itemView.findViewById(R.id.note_judul);
            tvPenulis = itemView.findViewById(R.id.note_penulis);


        }
    }

}
