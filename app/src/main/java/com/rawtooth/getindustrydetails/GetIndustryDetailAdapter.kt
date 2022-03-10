package com.rawtooth.getindustrydetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rawtooth.admin_waste.R
import com.rawtooth.getuserdetail.UserModelItem

class GetIndustryDetailAdapter(val context: Context) :RecyclerView.Adapter<GetIndustryDetailAdapter.GetIndustryUserVieHolder>() {

    var article:ArrayList<GetIndustryDetailsModelItem> = ArrayList<GetIndustryDetailsModelItem>()
    fun setData(a : ArrayList<GetIndustryDetailsModelItem>) {
        article = a;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetIndustryUserVieHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.get_indusrty_detail_item_layout,parent,false)
        return  GetIndustryUserVieHolder(view)
    }

    override fun onBindViewHolder(holder: GetIndustryUserVieHolder, position: Int) {
        val articles=article[position]
        holder.id.text= articles.id.toString()
        holder.name.text=articles.name
        holder.description.text=articles.description
        holder.industryType.text=articles.industryType
        holder.sector.text=articles.sector
        holder.email.text=articles.email
        holder.address.text=articles.address
    }

    override fun getItemCount(): Int {
     return  article.size
    }
    class GetIndustryUserVieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id = itemView.findViewById<TextView>(R.id.get_industry_details_id)
        var  name= itemView.findViewById<TextView>(R.id.get_industry_details_name)
        var description = itemView.findViewById<TextView>(R.id.get_industry_details_description)
        var industryType = itemView.findViewById<TextView>(R.id.get_industry_details_industryType)
        var  sector= itemView.findViewById<TextView>(R.id.get_industry_details_sector)
        var email = itemView.findViewById<TextView>(R.id.get_industry_details_email)
        var address = itemView.findViewById<TextView>(R.id.get_industry_details_address)
    }
}