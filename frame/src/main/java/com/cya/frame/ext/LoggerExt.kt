package com.cya.frame.ext

import com.orhanobut.logger.Logger


fun String.logI() = log(Level.I, this)
fun String.logE() = log(Level.E, this)
fun String.logD() = log(Level.D, this)
fun String.logW() = log(Level.W, this)
fun String.logV() = log(Level.V, this)

private enum class Level {
    I, E, D, W, V
}

private fun log(level: Level, msg: String) {
    when (level) {
        Level.I -> Logger.i(msg)
        Level.V -> Logger.v(msg)
        Level.E -> Logger.e(msg)
        Level.D -> Logger.d(msg)
        Level.W -> Logger.w(msg)
    }
}