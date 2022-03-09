package com.rawtooth.getAllCategory

import android.content.Context
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rawtooth.admin_waste.R

class Adapter(val context: Context) : RecyclerView.Adapter<Adapter.itemViewHolder>() {
    var articles : ArrayList<GetAllModelItem> = ArrayList<GetAllModelItem>()

    fun setData(a : ArrayList<GetAllModelItem>) {
        articles = a;
        notifyDataSetChanged()
    }

    fun addData(a : ArrayList<GetAllModelItem>) {
        articles.addAll(a)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
     val view=LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return itemViewHolder(view)
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val article=articles[position]
        val imageByteArray: ByteArray = Base64.decode(article.picByte, Base64.DEFAULT)
        holder.title.text=article.categoryName
        holder.decp.text=article.description
        Glide.with(context).load(imageByteArray).into(holder.image)
    }

    override fun getItemCount(): Int {
    return articles.size
    }

    class itemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView>(R.id.item_imgView)
        var title = itemView.findViewById<TextView>(R.id.item_title)
        var decp = itemView.findViewById<TextView>(R.id.item_desc)
    }
}