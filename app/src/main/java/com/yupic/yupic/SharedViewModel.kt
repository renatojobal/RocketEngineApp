package com.yupic.yupic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.yupic.yupic.model.*
import com.yupic.yupic.ui.NODE_TYPE_MULTIPLE_CHOICE
import timber.log.Timber


class SharedViewModel() : ViewModel() {

    private val _projectsList: MutableLiveData<List<Project>> = MutableLiveData(listOf())
    val projectsList: LiveData<List<Project>> = _projectsList

    private val _categories: MutableLiveData<List<Category>?> = MutableLiveData(null)
    val categories: LiveData<List<Category>?> = _categories

    /**
     * Dark theme
     */
    private val _darkThemeOn = MutableLiveData(false)
    var darkThemeOn: LiveData<Boolean> = _darkThemeOn
    fun toggleTheme() {
        _darkThemeOn.value = _darkThemeOn.value?.not()
    }


}