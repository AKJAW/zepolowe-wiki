package com.example.myapplication.game_fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.link_item.view.*

class LinkAdapter(var links: List<String>): RecyclerView.Adapter<LinkAdapter.LinkViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LinkViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val view = inflater.inflate(R.layout.link_item, viewGroup, false)
        return LinkViewHolder(view)
    }

    override fun getItemCount(): Int = links.size

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        holder.linkTitle.text = links[position]
    }


    class LinkViewHolder(linkView: View): RecyclerView.ViewHolder(linkView){
        val linkTitle: TextView = linkView.link_title_text_view
    }
}