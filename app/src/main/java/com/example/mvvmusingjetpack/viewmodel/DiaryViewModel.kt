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

    //val getUnSyncDataList = getDataListBySyncStatus(false)

private val itmsUnsync = MutableLiveData<List<DiaryData>>()
     fun getAllNotesUnsynced():LiveData<List<DiaryData>> {
         val datalist = ArrayList<DiaryData>()
         viewModelScope.launch(Dispatchers.IO) {
             datalist.addAll(repository.getDataListBySyncStatus(0))
             itmsUnsync.postValue(datalist)
         }

        return itmsUnsync
    }
//val diars = ArrayList<DiaryData>()
//    fun getDataListBySyncStatus(isSync: Int):LiveData<List<DiaryData>> {
//        val datas = MutableLiveData<List<DiaryData>>()
//         viewModelScope.launch {
//             repository.getDataListBySyncStatus(isSync).value?.let { diars.addAll(it) }
//         }
//        return datas
//        }


//    }
    suspend fun getUnSyncData(isSync: Int): List<DiaryData> {
        return repository.getDataListBySyncStatus(isSync)
    }








}