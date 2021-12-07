package com.example.firsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_song.*

class SongHolder(
    override val containerView: View,
    private val itemClick: (Song) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private var song: Song? = null

    init {
        itemView.setOnClickListener {
            song?.also(itemClick)
        }
    }

    fun bind(song: Song) {
        this.song = song
        with(song) {
            tv_title.text = title
            tv_author.text = author
            iv_cover.setImageResource(cover)
        }
    }

    companion object {

        fun create(parent: ViewGroup, itemClick: (Song) -> Unit): SongHolder =
            SongHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.card_song, parent, false),
                itemClick
            )

    }
}