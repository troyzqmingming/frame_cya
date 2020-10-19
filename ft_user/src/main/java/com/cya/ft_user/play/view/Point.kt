package com.cya.ft_user.play.view

/**
 * 涟漪粒子
 * @packageName: com.cya.ft_user.play
 * @author: zengqingming
 * @date: 2020/10/9
 */
data class Point(
    var x: Float,
    var y: Float,
    var radius: Float,
    var speed: Float,//速度
    var alpha: Int,//透明度
    var maxOffset: Float = 300f//最大移动距离
)