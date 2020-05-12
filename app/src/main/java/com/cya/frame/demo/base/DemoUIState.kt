package com.cya.frame.demo.base

sealed class DemoUIState

object ShowLoading : DemoUIState()

object HideLoading : DemoUIState()