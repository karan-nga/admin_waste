package com.rawtooth.getngodetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rawtooth.admin_waste.R
import com.rawtooth.getuserdetail.GetUserDetailAdapter
import com.rawtooth.getuserdetail.UserModelItem

class GetNgoDetailAdapter(val context:Context):RecyclerView.Adapter<GetNgoDetailAdapter.GetNgoDetailsVieHolder>() {

    var article:ArrayList<GetNgoDetailsModelItem> = ArrayList<GetNgoDetailsModelItem>()
    fun setData(a : ArrayList<GetNgoDetailsModelItem>) {
        article = a;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetNgoDetailsVieHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_get_user_details,parent,false)
        return GetNgoDetailsVieHolder(view)
    }

    override fun onBindViewHolder(holder: GetNgoDetailsVieHolder, position: Int) {
        val article=article[position]

        holder.name.text=article.name
        holder.description.text= holder.description.toString()
        holder.location.text=holder.location.toString()
        holder.ngoType.text=holder.ngoType.toString()
    }

    override fun getItemCount(): Int {
   return article.size
    }
    class GetNgoDetailsVieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ngoId=itemView.findViewById<TextView>(R.id.getngodetailitemlayout_id)
        var name=itemView.findViewById<TextView>(R.id.getngodetailitemlayout_name)
        var description=itemView.findViewById<TextView>(R.id.get_industry_details_description)
        var location=itemView.findViewById<TextView>(R.id.getngodetailitemlayout_location)
        var ngoType=itemView.findViewById<TextView>(R.id.getngodetailitemlayout_ngoType)
    }
}