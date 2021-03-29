package com.example.mynote
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_detail.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var noteList = ArrayList<Note>()
        for (i in 0..10)
            noteList.add(Note("title$i","description$i"))
       val NoteRecycleAdapter =NoteViewAdapter(noteList)
         note_recycleView.apply {
            adapter = NoteRecycleAdapter
             layoutManager = LinearLayoutManager(this@MainActivity)
         }

        save_but.setOnClickListener(){
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_detail,null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Note Form")
            val  mAlertDialog = mBuilder.show()
            mAlertDialog.dialog_save.setOnClickListener(){
                mAlertDialog.dismiss()
            }
            mAlertDialog.dialog_cancle.setOnClickListener(){
                mAlertDialog.dismiss()
            }

        }

    }

}