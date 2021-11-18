package com.example.firsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firsapp.databinding.ItemBinding

class ItemAdapter: RecyclerView.Adapter<ItemAdapter.ItemHolder>() {
    val itemlist= ArrayList<Item>()
    class ItemHolder (view: View):  RecyclerView.ViewHolder(view){
        val binding = ItemBinding.bind(view)
        fun bind (item: Item) = with(binding){
            tvTitle.text = item.title
            tvDiscription.text = item.discription
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder , position: Int) {
        holder.bind(itemlist[position])
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }

}
