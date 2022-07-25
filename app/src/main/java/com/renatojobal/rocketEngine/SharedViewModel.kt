package com.renatojobal.rocketEngine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.renatojobal.rocketEngine.repository.AnnotateResponse
import com.renatojobal.rocketEngine.repository.ApiAnnotate
import com.renatojobal.rocketEngine.repository.ApiResources
import com.renatojobal.rocketEngine.repository.Entity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class SharedViewModel(
    private val api : ApiAnnotate = ApiAnnotate.create(),
    private val apiResources : ApiResources = ApiResources.create()
) : ViewModel() {

    private val _entities: MutableLiveData<List<Entity>?> = MutableLiveData(null)
    val entities: LiveData<List<Entity>?> = _entities

    val selectedEntity: MutableLiveData<Entity?> = MutableLiveData(null)

    val fullEntityInfo: MutableLiveData<JsonObject?> = MutableLiveData(null)
    val relatedEntities : MutableLiveData<JsonObject?> = MutableLiveData(null)

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
                // Populate categories list
                _entities.value = response.body()?.entities
            }

            override fun onFailure(call: Call<AnnotateResponse>, t: Throwable) {
                Timber.e(t, "Error while calling spotlight api")
            }
        })

    }

    /**
     * Get more info about a specific resource
     */
    fun getEntityInfo(){
        selectedEntity.value?.let { safeEntity ->

            val resource = safeEntity.uri.substringAfter("http://dbpedia.org/")
            val resourceType = resource.split("/")[0]
            val resourceName = resource.split("/")[1]

            Timber.d("Requesting info for $resourceName")

            val request = apiResources.getResource(resourceType = resourceType, resourceName = resourceName)

            request.enqueue(object : Callback<JsonObject>{
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                    // Get the entity info under: http://dbpedia.org/{resourceName} ->
                    val onlyTargetInfo = response.body()?.getAsJsonObject("http://dbpedia.org/${resourceType}/${resourceName}")

                    Timber.i("Full info: $onlyTargetInfo")
                    Timber.i("Success")
                    fullEntityInfo.value = onlyTargetInfo

                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Timber.e(t, "Error while calling dbpedia resource")
                }
            })

        }


    }



}