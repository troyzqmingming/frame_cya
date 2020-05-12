package com.cya.frame.base.vm

import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseViewModel<V : ViewBinding, R : BaseRepository>(
    val repository: R
) :
    ViewModel()