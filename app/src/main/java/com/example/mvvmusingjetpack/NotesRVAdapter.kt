package com.example.mvvmusingjetpack

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmusingjetpack.db.DiaryData
import java.util.ArrayList

class NotesRVAdapter(val context: Context, val listener:INotesRVAdapter) : RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {
    val allNote = ArrayList<DiaryData>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.text)
        val deleteButton = itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClicked(allNote[viewHolder.adapterPosition])

        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNote[position]
        holder.textView.text = currentNote.text

    }

    override fun getItemCount(): Int {
        return allNote.size
    }
    fun updateList(newList : List<DiaryData>){
        allNote.clear()
        allNote.addAll(newList)
        notifyDataSetChanged()
    }
}
interface INotesRVAdapter{
    fun onItemClicked(note: DiaryData)
}