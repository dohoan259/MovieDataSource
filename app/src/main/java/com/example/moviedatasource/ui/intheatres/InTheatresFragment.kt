package com.example.moviedatasource.ui.intheatres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviedatasource.databinding.FragmentIntheatresBinding
import com.example.moviedatasource.ui.base.BaseFragment

class InTheatresFragment : BaseFragment() {

    private lateinit var binding: FragmentIntheatresBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntheatresBinding.inflate(layoutInflater)
        return binding.root
    }
}