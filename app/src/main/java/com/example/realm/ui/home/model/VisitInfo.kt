package com.example.realm.ui.home.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class VisitInfo : RealmObject() {

    @PrimaryKey
    var id = UUID.randomUUID().toString()
    var visitCount = 0
}

fun VisitInfo.updateCount(): VisitInfo {
    return apply {
        visitCount++
    }
}