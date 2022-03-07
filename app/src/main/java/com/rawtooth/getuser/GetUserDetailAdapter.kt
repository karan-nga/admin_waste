package com.rawtooth.getuser

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rawtooth.admin_waste.R
import com.rawtooth.getAllCategory.GetAllModelItem

class GetUserDetailAdapter(val context:Context) : RecyclerView.Adapter<GetUserDetailAdapter.GetUserVieHolder>() {
 var article:ArrayList<UserModelItem> = ArrayList<UserModelItem>()
    fun setData(a : ArrayList<UserModelItem>) {
        article = a;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetUserVieHolder {
        val view =LayoutInflater.from(context).inflate(R.layout.item_get_user_details,parent,false)
        return GetUserVieHolder(view)
    }

    override fun onBindViewHolder(holder: GetUserVieHolder, position: Int) {
        val article=article[position]
        holder.username.text=article.username
        holder.phonenumber.text=article.phonenumber
        holder.email.text=article.email
    }

    override fun getItemCount(): Int {
        return  article.size
    }

    class GetUserVieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var username = itemView.findViewById<TextView>(R.id.getUseDetailsName)
        var phonenumber = itemView.findViewById<TextView>(R.id.getUseDetailsPhone)
        var email = itemView.findViewById<TextView>(R.id.getUseDetailsemail)
    }
}