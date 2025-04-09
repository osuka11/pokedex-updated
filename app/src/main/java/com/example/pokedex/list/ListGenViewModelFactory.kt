package com.example.pokedex.list

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListGenViewModelFactory(private val application: FragmentActivity,
                              private val offset:Int,
                              private val limit: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListGenViewModel(application, offset, limit) as T
    }
}