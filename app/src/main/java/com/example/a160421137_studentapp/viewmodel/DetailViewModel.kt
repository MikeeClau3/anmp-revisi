package com.example.a160421137_studentapp.viewmodel
import  android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160421137_studentapp.model.Student
import com.google.gson.Gson

class DetailViewModel(application: Application):AndroidViewModel(application){
    val studentLD = MutableLiveData<Student>()
    val TAG = "volleyTag"
    private var queue:RequestQueue?= null
    fun fetch() {
//        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
//            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
//
//        studentLD.value = student1

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php?id=${studentLD}"

        val stringRequest = StringRequest (Request.Method.GET,
            url, {
                    response ->
                studentLD.value = Gson().fromJson(response, Student::class.java)
                Log.d("showVolley", response)
            }, {
                Log.d("error", it.toString())
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