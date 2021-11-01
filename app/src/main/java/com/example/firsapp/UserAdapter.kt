package com.example.firsapp

import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager

class UserAdapter (
    private val list:List<User> ,
    private val glide: RequestManager ,
    private val action: (Int) -> Unit
): RecyclerView.Adapter<UserHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup ,
                                    viewType: Int
    ): UserHolder = UserHolder.create(parent,glide,action)

    override fun onBindViewHolder(holder: UserHolder , position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}
