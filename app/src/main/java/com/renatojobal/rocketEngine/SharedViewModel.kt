package com.renatojobal.rocketEngine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.renatojobal.rocketEngine.repository.AnnotateResponse
import com.renatojobal.rocketEngine.repository.ApiInterface
import com.renatojobal.rocketEngine.repository.Entity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class SharedViewModel(
    private val api : ApiInterface = ApiInterface.create()
) : ViewModel() {

    private val _entities: MutableLiveData<List<Entity>?> = MutableLiveData(null)
    val entities: LiveData<List<Entity>?> = _entities


    /**
     * Dark theme
     */
    private val _darkThemeOn = MutableLiveData(false)
    var darkThemeOn: LiveData<Boolean> = _darkThemeOn
    fun toggleTheme() {
        _darkThemeOn.value = _darkThemeOn.value?.not()
    }

    /**
     * Here we weill get the annotated text calling the api of spotlight
     */
    fun handleSearch(rawText: String){

        val request = api.annotate(rawText = rawText)

        request.enqueue(object : Callback<AnnotateResponse>{
            override fun onResponse(call: Call<AnnotateResponse>, response: Response<AnnotateResponse>) {
                Timber.d("Response ${response.body().toString()}")
                Timber.d("Got it")

                // Populate categories list
                _entities.value = response.body()?.entities
            }

            override fun onFailure(call: Call<AnnotateResponse>, t: Throwable) {
                Timber.e(t, "Error while calling spotlight api")
            }
        })


    }



}