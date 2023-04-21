package com.example.realm.ui.deleteviews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.realm.ui.home.model.VisitInfo
import io.realm.Realm

class DeleteViewsViewModels : ViewModel() {

    private val db = Realm.getDefaultInstance()

    private val _visitInfo = MutableLiveData<VisitInfo>()
    val visitInfo: LiveData<Int> = _visitInfo.map { visitInfo ->
        visitInfo.visitCount
    }

    fun deleteViewCount(count: Int) {
        val visitInfo = db.where(VisitInfo::class.java).findFirst()
        if (visitInfo != null) {
            db.beginTransaction()
            visitInfo.apply {
                visitCount = if (visitCount.minus(count) >= 0)
                    visitCount.minus(count)
                else
                    0
                _visitInfo.postValue(this)
            }
            db.commitTransaction()
        }
    }
}