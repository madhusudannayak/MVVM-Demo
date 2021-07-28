package com.example.mvvmusingjetpack.viewmodel

import android.app.Application
import android.content.ClipData.Item
import androidx.lifecycle.*
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.db.DiaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class DiaryViewModel(application: Application) : AndroidViewModel(application) {




    //val dao = DiaryDatabase.getDatabase(application).getDiaryDao()
    //

    val repository : DiaryRepository = DiaryRepository.getInstance((application.baseContext))

  //  val NotesByID : LiveData<List<DiaryData>> = repository.allNoteById

    val allNotes: LiveData<List<DiaryData>> = repository.allNote

    fun getNotesByID(id: String) : LiveData<List<DiaryData>>{
        return repository.allNoteById(id)
    }

    fun deleteNode(diaryData: DiaryData)   = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(diaryData)
    }
    fun insertNote(diaryData: DiaryData) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(diaryData)
    }

    fun updateData(diaryData: DiaryData) = viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(diaryData)
    }

    val getUnSyncDataList = viewModelScope.launch(Dispatchers.IO) {
         getDataListBySyncStatus(false)
    }

    private suspend fun getDataListBySyncStatus(isSync: Boolean):List<DiaryData> {
            return repository.getDataListBySyncStatus(isSync)

        }


//    }
//    suspend fun getUnSyncData(isSync: Boolean): LiveData<List<DiaryData>> {
//        return repository.getUnSyncData(isSync)
//    }








}