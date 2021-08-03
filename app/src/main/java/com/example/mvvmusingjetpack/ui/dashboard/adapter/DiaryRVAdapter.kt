package com.example.mvvmusingjetpack.ui.dashboard.adapter

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

class DiaryRVAdapter(val context: Context?, private val listener: IDiaryRVAdapter) : RecyclerView.Adapter<DiaryRVAdapter.DiaryViewHolder>() {

    private val allNote = ArrayList<DiaryData>()

    inner class DiaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.note)
        val Bgcolor = itemView.findViewById<LinearLayout>(R.id.color)
        val delete = itemView.findViewById<ImageView>(R.id.delete)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val viewHolder = DiaryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_diary, parent, false))
        listener.size(allNote.size.toString())
        viewHolder.delete.setOnClickListener {
            listener.deleteNote(allNote[viewHolder.adapterPosition])
        }

        viewHolder.textView.setOnClickListener {
            listener.onItemClicked(allNote[viewHolder.adapterPosition], allNote.size.toString())
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        val currentNote = allNote[position]
        holder.textView.text = currentNote.text

        when (currentNote.color)
        {
            Color.WHITE -> holder.Bgcolor.setBackgroundColor(android.graphics.Color.WHITE)
            Color.BLUE -> holder.Bgcolor.setBackgroundColor(android.graphics.Color.parseColor("#87cdff"))
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

interface IDiaryRVAdapter {
    fun onItemClicked(note: DiaryData, size: String)
    fun size(size: String)
    fun deleteNote(note: DiaryData)
}