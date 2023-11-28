package com.robgar.marvel.core.ui.superhero_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SuperheroViewModel @Inject constructor() : ViewModel() {

    private val _showComicDialog = MutableLiveData<Boolean>()
    val showComicDialog : LiveData<Boolean> = _showComicDialog

    private val _showSerieDialog = MutableLiveData<Boolean>()
    val showSerieDialog : LiveData<Boolean> = _showSerieDialog

    fun onComicDialogClose() {
        _showComicDialog.value = false
    }

    fun onShowComicDialogClick() {
        _showComicDialog.value = true
    }

    fun onSerieDialogClose() {
        _showSerieDialog.value = false
    }

    fun onShowSerieDialogClick() {
        _showSerieDialog.value = true
    }
}