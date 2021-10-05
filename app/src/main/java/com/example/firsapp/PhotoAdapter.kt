package com.example.firsapp

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firsapp.databinding.PhotoItemBinding
import java.util.ArrayList

class PhotoAdapter: RecyclerView.Adapter<PhotoAdapter.PhotoHolder>() {
    public val photolist = ArrayList<Photos>()
    class PhotoHolder(item: View):RecyclerView.ViewHolder(item) {
        val binding= PhotoItemBinding.bind(item)
        fun bind(photo: Photos) = with(binding){
            ivPhoto1.setImageResource(photo.imageID)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_item,parent,false)
        return PhotoHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.bind(photolist[position])
    }

    override fun getItemCount(): Int {
        return photolist.size
    }
    fun addPhoto(photo: Photos){
        photolist.add(photo)
        notifyDataSetChanged()
    }
}
