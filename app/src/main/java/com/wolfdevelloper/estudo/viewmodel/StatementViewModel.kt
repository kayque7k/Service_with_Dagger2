package com.wolfdevelloper.estudo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class StatementViewModel (
    var list: MutableLiveData<MutableList<Statement>> = MutableLiveData(ArrayList())
) : ViewModel()