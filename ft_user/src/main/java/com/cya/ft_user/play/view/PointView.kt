package com.cya.ft_user.play.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.cya.frame.ext.randomColor
import com.cya.frame.ext.yes
import java.util.*

/**
 * @packageName: com.cya.ft_user.play.view
 * @author: zengqingming
 * @date: 2020/10/9
 */
class PointView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    //粒子集合
    private var pointList = mutableListOf<Point>()

    //画笔
    private var paint = Paint()

    private var centerX: Float = 0f
    private var centerY: Float = 0f

    private val anim = ValueAnimator.ofFloat(0f, 1f)

    private val random = Random()

    private val path = Path()
    private val pathMeasure = PathMeasure()
    private var pos = FloatArray(2)//扩散圆上某一点的x,y
    private val tan = FloatArray(2)//扩散圆上某一点切线


    private fun updatePoint() {
        pointList.forEach {
            it.alpha = ((1f - (it.y - centerY) / it.maxOffset) * 225f).toInt()
            //超过最大距离重新设置位置
            (it.y - centerY > it.maxOffset).yes {
                it.y = centerY + random.nextInt(6) - 3f
                it.x = random.nextInt((centerX * 2).toInt()).toFloat()
                it.speed = (random.nextInt(10) + 5).toFloat()
            }
            it.y += it.speed
        }
    }

    init {
        anim.apply {
            duration = 2000
            repeatCount = -1
            interpolator = LinearInterpolator()
            addUpdateListener {
                //更新粒子位置（下坠）
                updatePoint()
                invalidate()
            }
        }
        paint.color = randomColor()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.isAntiAlias = true
        pointList.forEach {
            paint.alpha = it.alpha
            canvas?.drawCircle(it.x, it.y, it.radius, paint)
        }

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = (w / 2).toFloat()
        centerY = (h / 2).toFloat()
        //原型
//        path.addCircle(centerX, centerY, 280f, Path.Direction.CCW)
//        pathMeasure.setPath(path, false)
        repeat((0 until 300).count()) {
//            pathMeasure.getPosTan(it / 500 * pathMeasure.length, pos, tan)
            val randomX = random.nextInt((centerX * 2).toInt())
            val randomY = centerY
            val randomSpeed = random.nextInt(10) + 5
            pointList.add(
                Point(
                    randomX.toFloat(),
                    randomY,
                    5f,
                    randomSpeed.toFloat(),
                    100
                )
            )
        }
        anim.start()
    }
}