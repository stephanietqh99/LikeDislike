package com.example.likedislikelifecycle

import android.util.Log
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {

    //when rotate will no reset

    var likeCount: Int = 0
    var dislikeCount: Int = 0

    init{
        Log.d("ViewModel", "ViewModel created")
    }

    fun incrementLike(){
        likeCount++
    }

    fun incrementDislike(){
        dislikeCount++
    }

    override fun onCleared(){
        Log.d("ViewModel", "ViewModel destroyed")
        super.onCleared()
    }
}