package com.example.a160421137_studentapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a160421137_studentapp.R
import com.example.a160421137_studentapp.databinding.FragmentFilmListBinding
import com.example.a160421137_studentapp.viewmodel.filmViewModel


class filmListFragment : Fragment() {
    private lateinit var binding: FragmentFilmListBinding
    private lateinit var viewModel: filmViewModel
//    private val filmListAdapter = filmListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film_list, container, false)
    }


}