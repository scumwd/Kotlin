package com.example.firsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.firsapp.databinding.ItemUserBinding

class UserHolder(
 private val binding: ItemUserBinding,
 private val glide: RequestManager,
 private val action: (Int) -> Unit

):RecyclerView.ViewHolder(binding.root)
{
    fun bind(user: User) {

        with(binding){
            glide.load(user.url).into(ivPhoto)
            tvId.text = "ID: ${user.id}"
        }
        itemView.setOnClickListener {
            action(user.id)
        }
    }

    companion object{
        fun create(parent: ViewGroup,
                   glide: RequestManager,
                   action: (Int) -> Unit)
        =UserHolder(
            ItemUserBinding.inflate (
                LayoutInflater.from(parent.context),
                parent,
                false
            ),glide,action
        )
    }

}

