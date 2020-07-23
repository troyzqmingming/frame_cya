package com.cya.frame.demo.base.vm

import com.cya.frame.base.vm.BaseRepository
import com.cya.frame.base.vm.BaseViewModel

abstract class DemoBaseViewModel<R : BaseRepository>(repository: R) :
    BaseViewModel<R>(repository)