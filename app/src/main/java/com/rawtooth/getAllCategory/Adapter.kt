package com.rawtooth.getAllCategory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
        holder.title.text=article.categoryName
        holder.decp.text=article.description
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