package com.example.lab6_2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class Coroutine   : ViewModel(){
    private val mutableLiveImage = MutableLiveData<Bitmap>()

    val imageBm: LiveData<Bitmap> get() = mutableLiveImage

    fun load(url: String) {
        viewModelScope.launch {

            mutableLiveImage.value = async(Dispatchers.IO) {
                java.net.URL(url).openStream().use {
                    return@async BitmapFactory.decodeStream(it)
                }
            }.await()
        }
    }

}