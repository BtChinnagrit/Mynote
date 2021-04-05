package com.example.mynote


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_note_detail.view.*
import kotlinx.android.synthetic.main.note_item.view.*
import java.io.IOException


class NoteViewAdapter(var notelist: ArrayList<Note>,var onNoteClicklistener: OnNoteClicklistener):RecyclerView.Adapter<NoteViewAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_item,
            parent,
            false
        )
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        var current = notelist[position]
        holder.title.text =current.title
        holder.description.text = current.description
        Picasso.get().load(current.url).into(holder.urls)
        holder.click(onNoteClicklistener)


    }

    override fun getItemCount(): Int {
        return notelist.size
    }

    class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var title = itemView.title_textView
        var description = itemView.des_textView
        var urls = itemView.icon_view

        fun click(act:OnNoteClicklistener){
            itemView.setOnClickListener{
                act.onclick(adapterPosition)
            }
        }

    }
}
interface  OnNoteClicklistener{
    fun onclick(position: Int)

}