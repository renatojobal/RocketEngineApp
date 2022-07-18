package com.renatojobal.rocketEngine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.renatojobal.rocketEngine.model.*


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