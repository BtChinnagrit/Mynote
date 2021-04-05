package com.example.mynote

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset


class MainActivity : AppCompatActivity(),OnNoteClicklistener {
    var titlename: ArrayList<String> = ArrayList()
    var imageurl: ArrayList<String> = ArrayList()
    var descriptionJ: ArrayList<String> = ArrayList()
    var noteList = ArrayList<Note>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var isFirstRun =getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun",true)
        if (isFirstRun){

        }
        try {
            val obj = JSONObject(loadJSONFromAsset())
            val data = loadJSONFromAsset()
            val userArray = obj.getJSONArray("data")
            for (i in 0 until userArray.length()) {
                val Detail = userArray.getJSONObject(i)
                titlename.add(Detail.getString("title"))
                imageurl.add(Detail.getString("url"))
                descriptionJ.add(Detail.getString("description"))
                }
                for (i in 0 until userArray.length())
                    noteList.add(Note(titlename[i], imageurl[i], descriptionJ[i]))
                val NoteRecycleAdapter = NoteViewAdapter(noteList, this)
                note_recycleView.apply {
                    adapter = NoteRecycleAdapter
                    layoutManager = LinearLayoutManager(this@MainActivity)
                }
        }
        catch (e: JSONException) {
            e.printStackTrace()
        }
    }
    fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream = assets.open("Note.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }

    override fun onclick(position: Int) {
        val intent = Intent(this, note_detail::class.java)
        val bundle = Bundle()
        bundle.putParcelable("Note", noteList[position])
        intent.putExtras(bundle)
        startActivity(intent)
    }


}


