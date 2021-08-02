package com.example.mvvmusingjetpack.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.db.Color
import com.example.mvvmusingjetpack.db.DiaryData
import java.util.ArrayList

class SearchRVAdapter(val context: Context?) : RecyclerView.Adapter<SearchRVAdapter.DiaryViewHolder>() {

    val allNote = ArrayList<DiaryData>()

    class DiaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.note)
        val Bgcolor = itemView.findViewById<LinearLayout>(R.id.color)
        val delete = itemView.findViewById<ImageView>(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val viewHolder = DiaryViewHolder(LayoutInflater.from(context).inflate(R.layout.searchitem_diary, parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {

    val currentNote = allNote[position]
    holder.textView.text = currentNote.text


    when (currentNote.color) {
        Color.WHITE -> holder.Bgcolor.setBackgroundColor(android.graphics.Color.WHITE)
        Color.BLUE -> holder.Bgcolor.setBackgroundColor(android.graphics.Color.parseColor("#87CDFF"))
        Color.PINK -> holder.Bgcolor.setBackgroundColor(android.graphics.Color.parseColor("#F6CEE5"))
        Color.YELLOW -> holder.Bgcolor.setBackgroundColor(android.graphics.Color.parseColor("#EAD3AC"))
        Color.GREEN -> holder.Bgcolor.setBackgroundColor(android.graphics.Color.parseColor("#b0dea0"))
        Color.GRAY -> holder.Bgcolor.setBackgroundColor(android.graphics.Color.parseColor("#CBDFF1"))
    }

    }

    override fun getItemCount(): Int {
        return allNote.size
    }
    fun updateList(newList: List<DiaryData>) {
        allNote.clear()
        allNote.addAll(newList)
        notifyDataSetChanged()
    }
}