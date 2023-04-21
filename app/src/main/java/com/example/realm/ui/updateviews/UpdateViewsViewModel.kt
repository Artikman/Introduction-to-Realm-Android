package com.example.myapplication.ui.updateviews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.realm.ui.home.model.VisitInfo
import io.realm.Realm

class UpdateViewsViewModel : ViewModel() {

    private val db = Realm.getDefaultInstance()

    private val _visitInfo = MutableLiveData<VisitInfo>()
    val visitInfo: LiveData<Int> = _visitInfo.map { visitInfo ->
        visitInfo.visitCount
    }

    fun updateViewCount(count: Int) {
        val visitInfo = db.where(VisitInfo::class.java).findFirst()
        if (visitInfo == null) {
            db.executeTransactionAsync {
                val info = VisitInfo().apply {
                    visitCount = count
                }
                _visitInfo.postValue(info)
                it.insert(info)
            }
        } else {
            db.beginTransaction()
            visitInfo.apply {
                visitCount += count
                _visitInfo.postValue(this)
            }

            db.commitTransaction()
        }
    }
}