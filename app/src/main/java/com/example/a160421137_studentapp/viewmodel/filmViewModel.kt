package com.example.a160421137_studentapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160421137_studentapp.model.filmModel
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson


class filmViewModel(application: Application): AndroidViewModel(application) {
    var filmLD = MutableLiveData<ArrayList<filmModel>>()
    val filmLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue?=null

    fun refresh(){
        // bisa cari dari volley
        //query db sqLite
        //xml


        filmLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/film.json"


        val stringRequest = StringRequest(
            Request.Method.GET,
            url, {

                val sType = object : TypeToken<List<filmModel>>() {}.type
                val result = Gson().fromJson<List<filmModel>>(it, sType)
                filmLD.value = result as ArrayList<filmModel>?
                loadingLD.value = false

                Log.d("showvolley", result.toString())


            },
            {
                Log.d("showvolley", it.toString())
                loadingLD.value = false
                filmLoadErrorLD.value = false

            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}