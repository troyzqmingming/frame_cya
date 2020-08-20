package com.cya.frame.ext

import kotlin.random.Random

/**
 * 随检颜色
 */
fun Any.randomColor(): Int {
    return -0x1000000 or Random.nextInt(0x00ffffff)
}