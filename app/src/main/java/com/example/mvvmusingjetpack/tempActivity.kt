package com.example.mvvmusingjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.viewmodel.DiaryViewModel
import java.util.ArrayList

class tempActivity : AppCompatActivity(), INotesRVAdapter {
    lateinit var viewModel: DiaryViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var editText: EditText

    val allNote = ArrayList<DiaryData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp)

        editText = findViewById(R.id.input)
        recyclerView= findViewById(R.id.Recylerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this,this)
        recyclerView.adapter = adapter

        viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(DiaryViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {list ->
            list?.let {
                adapter.updateList(it)

                Log.d("sadasdasdsa", it.toString())


            }

        })

    }

    override fun onItemClicked(note: DiaryData) {
        viewModel.deleteNode(note)
    }

//    fun Submit(view: View) {
//        val noteText = editText.text.toString()
//        if(noteText.isNotEmpty()){
//            viewModel.insertNote(DiaryData(0,noteText))
//
//        }
//    }
}