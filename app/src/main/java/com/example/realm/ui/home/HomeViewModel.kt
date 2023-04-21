package com.example.realm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.realm.ui.home.model.VisitInfo
import com.example.realm.ui.home.model.updateCount
import io.realm.Realm

class HomeViewModel : ViewModel() {

    private val db = Realm.getDefaultInstance()
    private val _text = MutableLiveData("Welcome to Realm")
    val text: LiveData<String> = _text

    private val _visitInfo = MutableLiveData<VisitInfo>()

    // So, instead of using Transformations, you need to use the extension function directly
    // myLiveData.switchMap or myLiveData.map
    val visitInfo: LiveData<Int> = _visitInfo.map { visitInfo ->
        visitInfo.visitCount
    }

    init {
        updateData()
    }

    override fun onCleared() {
        super.onCleared()
        db.close()
    }

    fun onRefreshCount() {
        val visitInfo = db.where(VisitInfo::class.java).findFirst()
        visitInfo?.let {
            _visitInfo.value = it
        }
    }

    private fun updateData() {
        var visitInfo = db.where(VisitInfo::class.java).findFirst()
        visitInfo =
            visitInfo?.let { db.copyFromRealm(it).updateCount() } ?: VisitInfo().updateCount()
        updateCountToDB(visitInfo)
    }

    private fun updateCountToDB(info: VisitInfo) {
        db.executeTransactionAsync {
            it.copyToRealmOrUpdate(info)
            _visitInfo.postValue(info)
        }
    }
}