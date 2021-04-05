package com.example.mynote

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_detail.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
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
        try {
            val obj = JSONObject(loadJSONFromAsset())
            val data = loadJSONFromAsset()
            Toast.makeText(applicationContext, data, Toast.LENGTH_SHORT).show()
            val userArray = obj.getJSONArray("data")
            for (i in 0 until userArray.length()) {
                val Detail = userArray.getJSONObject(i)
                titlename.add(Detail.getString("title"))
                imageurl.add(Detail.getString("url"))
                descriptionJ.add(Detail.getString("description"))
                }
                for (i in 0 until userArray.length())
                    noteList.add(Note(titlename[i], imageurl[i], descriptionJ[i]))
                val NoteRecycleAdapter = NoteViewAdapter(noteList,this)
                note_recycleView.apply {
                    adapter = NoteRecycleAdapter
                    layoutManager = LinearLayoutManager(this@MainActivity)
                }
        }
        catch (e: JSONException) {
            e.printStackTrace()
        }
        save_but.setOnClickListener() {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_detail, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()
            mAlertDialog.dialog_save.setOnClickListener() {
                val title = mAlertDialog.title.text.toString()
                val url = mAlertDialog.image.text.toString()
                val description = mAlertDialog.des.text.toString()
                val data = Note(title, url, description)
                mAlertDialog.dismiss()
                if (title.isEmpty() || url.isEmpty() || description.isEmpty()) {
                    Toast.makeText(applicationContext, "You fill out incomplete information", Toast.LENGTH_SHORT).show()
                } else {
                    val jsonData = Json.encodeToString(data)
                    var gson = Gson()
                    var jsonString: String = gson.toJson(data)
                    try {
                        openFileOutput("Note.json", Context.MODE_PRIVATE).use { output ->
                            output.write(jsonData.toByteArray())
                        }
                        Toast.makeText(applicationContext, "Save", Toast.LENGTH_SHORT).show()
                    } catch (e: IOException) {
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                    }

                }

            }
            mAlertDialog.dialog_cancle.setOnClickListener() {
                mAlertDialog.dismiss()
            }


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
        val intent = Intent(this,note_detail::class.java)
        val bundle = Bundle()
        bundle.putParcelable("Note",noteList[position])
        intent.putExtras(bundle)
        startActivity(intent)
    }

}


