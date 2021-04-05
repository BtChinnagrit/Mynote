package com.example.mynote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_note_detail.*

class note_detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)
        val note =intent.getParcelableExtra<Note>("Note")
        if (note != null) {
            detail_title.text = note.title
        }
        if (note != null) {
            detail_des.text =note.description
        }
        if (note != null) {
            Picasso.get().load(note.url).into(detial_image)
        }


    }
}