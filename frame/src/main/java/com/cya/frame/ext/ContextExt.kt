package com.cya.frame.ext

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.TypedValue
import java.lang.Exception

val Context.versionName: String
    get() = packageManager.getPackageInfo(packageName, 0).versionName

val Context.versionCode: Long
    get() = with(packageManager.getPackageInfo(packageName, 0)) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) longVersionCode else versionCode.toLong()
    }

fun Context.isPackageInstalled(packageName: String): Boolean {
    return try {
        packageManager.getPackageInfo(packageName, 0)
        true
    } catch (e: Exception) {
        false
    }

}

val Context.screenWidth
    get() = resources.displayMetrics.widthPixels

val Context.screenHeight
    get() = resources.displayMetrics.heightPixels

fun Context.dp2Px(dp: Int): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp.toFloat(),
    resources.displayMetrics
).toInt()

fun Context.px2Dp(px: Int): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_PX,
    px.toFloat(),
    resources.displayMetrics
).toInt()


fun Context.call(phone: String) {
    val intent = Intent(Intent.ACTION_DIAL)
    with(Uri.parse("tel:$phone")) {
        intent.data = this
        startActivity(intent)
    }
}

fun Context.sendSMS(phone: String, msg: String) {
    val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$phone"))
    intent.putExtra("sms_body", msg)
    startActivity(intent)
}


