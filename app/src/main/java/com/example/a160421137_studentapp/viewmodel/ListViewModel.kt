package com.example.a160421137_studentapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160421137_studentapp.model.Student
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class ListViewModel(application: Application): AndroidViewModel(application) {
    var studentsLD = MutableLiveData<ArrayList<Student>>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue?=null

    fun refresh(){
        // bisa cari dari volley
        //query db sqLite
        //xml

        studentsLD.value = arrayListOf(
            Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100" +
                    ".jpg/cc0000/ffffff"),
            Student("13312","Rich","1994/12/14","3925444073","http://dummyimage.com/75x100" +
                    ".jpg/5fa2dd/ffffff"),
            Student("11204","Dinny","1994/10/07","6827808747","http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1")
        )
        studentLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url, {

                val sType = object : TypeToken<List<Student>>() {}.type
                val result = Gson().fromJson<List<Student>>(it, sType)
                studentsLD.value = result as ArrayList<Student>?

                Log.d("showvolley", it)
                loadingLD.value = false

            },
            {
                loadingLD.value = false
                studentLoadErrorLD.value = false
                Log.d("showvolley", it.toString())
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