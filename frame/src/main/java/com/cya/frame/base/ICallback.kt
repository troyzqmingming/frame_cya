package com.cya.frame.base

interface ICallback<T> {
    fun invoke(t: T)
}