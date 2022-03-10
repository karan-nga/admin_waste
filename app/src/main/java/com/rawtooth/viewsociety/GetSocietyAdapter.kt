package com.rawtooth.viewsociety

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

class GetSocietyAdapter(val context: Context):RecyclerView.Adapter<GetSocietyAdapter.itemViewHolder>() {
    var articles : ArrayList<SocietyDetailsModelItem> = ArrayList<SocietyDetailsModelItem>()

    fun setData(a: ArrayList<SocietyDetailsModelItem>) {
        articles = a;
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.get_society_details_items_layout,parent,false)
        return itemViewHolder(view)
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val article=articles[position]
        val imageByteArray: ByteArray = Base64.decode(article.picByte, Base64.DEFAULT)
        holder.name.text=article.name
        holder.email.text=article.email
        holder.address.text=article.address
        Glide.with(context).load(imageByteArray).into(holder.image)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class itemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val name=itemView.findViewById<TextView>(R.id.getSocietyDeatils_name)
        val email=itemView.findViewById<TextView>(R.id.igetSocietyDeatils_email)
        val address=itemView.findViewById<TextView>(R.id.getSocietyDeatils_address)
        val image=itemView.findViewById<ImageView>(R.id.getSocietyDeatils_imgView)
    }
}