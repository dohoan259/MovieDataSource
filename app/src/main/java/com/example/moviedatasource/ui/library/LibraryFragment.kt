package com.example.moviedatasource.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviedatasource.databinding.FragmentLibraryBinding
import com.example.moviedatasource.ui.base.BaseFragment

class LibraryFragment : BaseFragment() {

    private lateinit var binding: FragmentLibraryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLibraryBinding.inflate(layoutInflater)
        return binding.root
    }
}