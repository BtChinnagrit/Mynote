package com.example.mynote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*

class NoteViewAdapter(var notelist: ArrayList<Note>):RecyclerView.Adapter<NoteViewAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        var current = notelist[position]
        holder.blind(current)

    }

    override fun getItemCount(): Int {
        return notelist.size
    }

    class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var title = itemView.title_textView
        var description = itemView.des_textView

        fun blind(note:Note){
            title.text = note.title
            description.text = note.description
        }
    }
}