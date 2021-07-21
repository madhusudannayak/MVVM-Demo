package com.example.mvvmusingjetpack.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.db.DiaryDatabase
import com.example.mvvmusingjetpack.db.DiaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiaryViewModel (application: Application) : AndroidViewModel(application) {


    //val dao = DiaryDatabase.getDatabase(application).getDiaryDao()
    //

    val repository : DiaryRepository = DiaryRepository(application.baseContext)

  //  val NotesByID : LiveData<List<DiaryData>> = repository.allNoteById

    val allNotes: LiveData<List<DiaryData>> = repository.allNote

    fun getNotesByID() : LiveData<List<DiaryData>>{
        repository.id = "8"
        return repository.allNoteById
    }

    fun deleteNode(diaryData: DiaryData)   = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(diaryData)
    }
    fun insertNote(diaryData: DiaryData) = viewModelScope.launch (Dispatchers.IO){
        repository.insert(diaryData)
    }


}