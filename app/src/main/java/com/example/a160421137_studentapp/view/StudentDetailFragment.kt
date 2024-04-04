package com.example.a160421137_studentapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a160421137_studentapp.R
import com.example.a160421137_studentapp.databinding.FragmentStudentDetailBinding
import com.example.a160421137_studentapp.viewmodel.DetailViewModel


class StudentDetailFragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding: FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.fetch()

        observeViewModel()
    }
    fun observeViewModel(){
        detailViewModel.studentLD.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                binding.txtId.setText(it.id)
                binding.txtMake.setText(it.name)
                binding.txtBirth.setText(it.dob)
                binding.txtPhone.setText(it.phone)
            }
        })
    }



}