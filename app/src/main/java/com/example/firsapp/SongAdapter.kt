package com.example.firsapp

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(
    private var list: List<Song>,
    private val itemClick: (Song) -> Unit
) : RecyclerView.Adapter<SongHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        return SongHolder.create(parent, itemClick)
    }

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        return holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}